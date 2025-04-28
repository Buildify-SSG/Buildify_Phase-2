# 창고 면적 계산 트리거
# userWareHouse테이블 업데이트시 warehouse_area 테이블 업데이트


DELIMITER //

-- AFTER UPDATE 트리거
CREATE TRIGGER trg_update_available_space_userwarehouse_update
    AFTER UPDATE ON userWareHouse
    FOR EACH ROW
BEGIN
    DECLARE total_usage DECIMAL(10, 2);
    DECLARE total_size DECIMAL(10, 2);

    -- userWareHouse로부터 해당 창고의 전체 사용량 계산
    SELECT IFNULL(SUM(warehouse_usage), 0)
    INTO total_usage
    FROM userWareHouse
    WHERE ware_id = NEW.ware_id;

    -- warehouse_area의 총 크기 조회
    SELECT ware_total_size
    INTO total_size
    FROM warehouse_area
    WHERE ware_id = NEW.ware_id;

    -- available_space 업데이트
    UPDATE warehouse_area
    SET available_space = GREATEST(total_size - total_usage, 0)
    WHERE ware_id = NEW.ware_id;
END //

DELIMITER ;

SHOW TRIGGERS