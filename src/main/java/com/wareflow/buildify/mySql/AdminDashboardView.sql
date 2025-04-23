# 관리자 대시보드 입고 현황 뷰
CREATE OR REPLACE VIEW v_inbound_stats AS
WITH RECURSIVE dates AS (
    SELECT CURDATE() - INTERVAL 365 DAY AS date
    UNION ALL
    SELECT date + INTERVAL 1 DAY
    FROM dates
    WHERE date + INTERVAL 1 DAY <= CURDATE()
)
SELECT
    d.date,
    COUNT(DISTINCT i.inbound_id) AS requestCount,
    COUNT(DISTINCT p.inbound_id) AS approvalCount
FROM dates d
         LEFT JOIN inbound i ON DATE(i.req_inbound_date) = d.date
         LEFT JOIN inbound p ON DATE(p.inbound_process_date) = d.date AND p.inbound_status = 1
GROUP BY d.date
ORDER BY d.date;

# 관리자 대시보드 출고 현황 뷰
CREATE OR REPLACE VIEW v_outbound_stats AS
WITH RECURSIVE dates AS (
    SELECT CURDATE() - INTERVAL 365 DAY AS date
    UNION ALL
    SELECT date + INTERVAL 1 DAY
    FROM dates
    WHERE date + INTERVAL 1 DAY <= CURDATE()
)
SELECT
    d.date,
    COUNT(DISTINCT o.outbound_id) AS requestCount,
    COUNT(DISTINCT p.outbound_id) AS approvalCount
FROM dates d
         LEFT JOIN outbound o ON DATE(o.req_outbound_date) = d.date
         LEFT JOIN outbound p ON DATE(p.outbound_process_date) = d.date AND p.status = 1
GROUP BY d.date
ORDER BY d.date;


# 창고별 계약률/가용률 view
CREATE OR REPLACE VIEW v_ware_dashboard AS
select area.ware_id,(count(user.ware_id)/25) as 계약률,(area.available_space/area.ware_total_size) as 가용률
from userWareHouse user
         join warehouse_area area
              on user.ware_id = area.ware_id
group by area.ware_id;
