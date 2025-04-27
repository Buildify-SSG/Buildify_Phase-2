DROP PROCEDURE  IF EXISTS inbound_approve;

DELIMITER $$

CREATE PROCEDURE inbound_approve(
    IN in_inventory_id VARCHAR(20),
    IN in_client_id VARCHAR(20),
    IN in_ware_id VARCHAR(10),
    IN in_pos_x VARCHAR(10),
    IN in_pos_y INTEGER,
    IN in_prod_id VARCHAR(20),
    IN in_quantity INT,
    IN in_new_usage DECIMAL,
    IN in_inbound_id VARCHAR(30)
)
BEGIN
START TRANSACTION;
IF in_inventory_id IS NULL OR in_inventory_id = '' THEN
    SET in_inventory_id = CONCAT('INV-', DATE_FORMAT(NOW(), '%Y%m%d'), LPAD(FLOOR(RAND()*10000),4,'0'));
END IF;

-- 입고 상태 변경
UPDATE inbound
SET Inbound_status = 1,
    inbound_process_date = NOW()
WHERE inbound_id = in_inbound_id;

-- 재고 수량 증가 및 출고일 업데이트
UPDATE inventory
SET quantity = quantity + in_quantity,
    last_inbound_date = NOW()
WHERE client_id = in_client_id
  AND ware_id = in_ware_id
  AND warehouse_pos_x = in_pos_x
  AND warehouse_pos_y = in_pos_y
  AND prod_id = in_prod_id;

-- 재고 없으면 INSERT (REPLACE 써도 가능)
INSERT INTO inventory
(inventory_id, prod_id, client_id, quantity, ware_id, last_inbound_date, warehouse_pos_x, warehouse_pos_y)
SELECT in_inventory_id,in_prod_id,in_client_id,in_quantity,in_ware_id,now(),in_pos_x,in_pos_y
FROM DUAL
WHERE NOT EXISTS (
    SELECT 1 FROM inventory
    WHERE client_id = in_client_id
      AND ware_id = in_ware_id
      AND warehouse_pos_x = in_pos_x
      AND warehouse_pos_y = in_pos_y
      AND prod_id = in_prod_id
);

UPDATE userWareHouse
SET warehouse_usage =
        IFNULL(warehouse_usage, 0)
            + IFNULL(in_new_usage, 0)
WHERE client_id = in_client_id
  AND ware_id   = in_ware_id
  AND warehouse_pos_x = in_pos_x
  AND warehouse_pos_y = in_pos_y;

COMMIT;
END $$

DELIMITER ;