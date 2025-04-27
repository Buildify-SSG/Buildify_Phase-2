delimiter //
CREATE PROCEDURE DB_inbound_check_client_read()
BEGIN
    -- 기존에 존재하는 상품이라면 수량만 업데이트
UPDATE inventory v
    JOIN inbound i
ON i.prod_id = v.prod_id
    AND i.client_id = v.client_id
    and i.warehouse

    SET v.quantity = v.quantity + i.quantity
WHERE i.inbound_status = 0;

-- 존재하지 않는 상품이라면 새로 추가
INSERT INTO inventory (prod_id, client_id, quantity, ware_id, last_inbound_day)
SELECT i.prod_id, i.client_id, i.quantity, 'ware1', now()
FROM inbound i
WHERE i.inbound_status = 0
  AND NOT EXISTS (
    SELECT 1 FROM inventory v
    WHERE v.prod_id = i.prod_id
      AND v.client_id = i.client_id

);





-- 입고 상태 변경
UPDATE inbound
SET inbound_status = 1, ware_id = 'ware1'
WHERE inbound_status = 0;



END;
DELIMITER //

DROP view v_inbound_approve_list;

CREATE OR REPLACE VIEW v_inbound_approve_list AS
select inbound.inbound_id      as inboundId,
       inbound.prod_id         as prodId,
       inbound.client_id       as clientId,
       inbound.quantity        as quantity,
       inbound.ware_id         as wareId,
       inbound.warehouse_pos_x as warePosX,
       inbound.warehouse_pos_y as warePosY,
       inventory.inventory_id  as inventoryId,
       product.prod_size       as prodSize
from inbound
        left join inventory
              on  inbound.ware_id = inventory.ware_id
                  and inbound.warehouse_pos_x = inventory.warehouse_pos_x
                  and inbound.warehouse_pos_y = inventory.warehouse_pos_y
                  and inbound.prod_id = inventory.prod_id
         left join product
                   on inbound.prod_id = product.prod_id;

select *
from v_inbound_approve_list

