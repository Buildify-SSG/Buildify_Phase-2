# 창고 면적 계산 트리거
# userWareHouse테이블 신규 계약시 warehouse_area 테이블 업데이트

DELIMITER //

CREATE TRIGGER trg_update_available_space_userwarehouse_insert
    AFTER INSERT ON userWareHouse
    FOR EACH ROW
BEGIN
    UPDATE warehouse_area
    SET available_space = GREATEST(
            (SELECT ware_total_size FROM warehouse_area WHERE ware_id = NEW.ware_id)
                - (SELECT IFNULL(SUM(warehouse_usage), 0) FROM userWareHouse WHERE ware_id = NEW.ware_id),
            0
                          )
    WHERE ware_id = NEW.ware_id;
END //

DELIMITER ;


# 창고 면적 계산 트리거
# userWareHouse테이블 업데이트시 warehouse_area 테이블 업데이트



DELIMITER //

CREATE TRIGGER trg_update_available_space_userwarehouse_update
    AFTER UPDATE ON userWareHouse
    FOR EACH ROW
BEGIN
    UPDATE warehouse_area
    SET available_space = GREATEST(
            (SELECT ware_total_size FROM warehouse_area WHERE ware_id = NEW.ware_id)
                - (SELECT IFNULL(SUM(warehouse_usage), 0) FROM userWareHouse WHERE ware_id = NEW.ware_id),
            0
                          )
    WHERE ware_id = NEW.ware_id;
END //

DELIMITER ;

SHOW TRIGGERS;
