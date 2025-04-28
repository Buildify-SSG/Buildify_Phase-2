DELIMITER //

CREATE PROCEDURE outbound_request(IN in_inventoryId VARCHAR(50), IN in_quantity INTEGER )
BEGIN
    -- 필요한 데이터 임시 저장
    DECLARE set_prodId VARCHAR(50);
    DECLARE set_clientId VARCHAR(50);
    DECLARE set_wareId VARCHAR(50);
    DECLARE set_warehousePosX VARCHAR(5);
    DECLARE set_warehousePosY INT;
    DECLARE set_outboundId VARCHAR(50);
    -- 1. inventory 테이블에서 필요한 데이터 SELECT
SELECT i.prod_id, i.client_id,  i.ware_id, i.warehouse_pos_x, i.warehouse_pos_y
INTO set_prodId, set_clientId, set_wareId, set_warehousePosX, set_warehousePosY
FROM inventory i
WHERE i.inventory_id = in_inventoryId;

-- inbound_id 생성
    SET set_outboundId = CONCAT('OUT-', DATE_FORMAT(NOW(), '%Y%m%d'), LPAD(FLOOR(RAND() * 10000), 4, '0'));
-- 2. SELECT로 가져온 값으로 INSERT

INSERT INTO outbound (
    outbound_id, prod_id, client_id, quantity, status, req_outbound_date, ware_id, warehouse_pos_x, warehouse_pos_y
)
VALUES (
          set_outboundId, set_prodId, set_clientId, in_quantity, 0, now(), set_wareId, set_warehousePosX, set_warehousePosY
       );
END //

DELIMITER ;


-- //////////////////////
<!-- MyBatis XML 쿼리: 아웃바운드 요청 승인 처리 -->
<update id="adminOutboundRequest">
    <!-- 1. 인벤토리 수량 차감 -->
    UPDATE inventory
    SET quantity = quantity - #{quantity}
    WHERE prod_id = (SELECT o.prod_id
                     FROM outbound o
                     WHERE o.outbound_id = #{outboundId})
      AND client_id = (SELECT o.client_id
                       FROM outbound o
                       WHERE o.outbound_id = #{outboundId})
      AND warehouse_pos_x = (SELECT o.warehouse_pos_x
                             FROM outbound o
                             WHERE o.outbound_id = #{outboundId})
      AND warehouse_pos_y = (SELECT o.warehouse_pos_y
                             FROM outbound o
                             WHERE o.outbound_id = #{outboundId});

    <!-- 2. 아웃바운드 상태 변경 -->
    UPDATE outbound
    SET status = 1,
        outbound_process_date = NOW()
    WHERE outbound_id = #{outboundId};

    <!-- 3. 창고 가용률 차감 -->
    UPDATE userWarehouse
    SET warehouse_usage = warehouse_usage - (
        #{quantity} *
        (SELECT p.prod_size
         FROM product p
         WHERE p.prod_id = #{prodId})
    )
    WHERE client_id = (SELECT o.client_id
                       FROM outbound o
                       WHERE o.outbound_id = #{outboundId})
      AND ware_id = (SELECT o.ware_id
                     FROM outbound o
                     WHERE o.outbound_id = #{outboundId})
      AND warehouse_pos_x = (SELECT o.warehouse_pos_x
                             FROM outbound o
                             WHERE o.outbound_id = #{outboundId})
      AND warehouse_pos_y = (SELECT o.warehouse_pos_y
                             FROM outbound o
                             WHERE o.outbound_id = #{outboundId});
</update>