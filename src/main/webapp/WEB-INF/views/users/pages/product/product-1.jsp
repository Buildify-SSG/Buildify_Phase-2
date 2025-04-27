<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="container mt-5">
    <div class="card mx-auto p-5 shadow rounded" style="max-width: 700px;">
        <h2 class="text-center mb-4 fw-bold">🛒 상품 등록</h2>

        <form method="post" action="<c:url value='/users/pages/product/product-1'/>">
            <div class="mb-3">
                <label class="form-label">브랜드</label>
                <input type="text" class="form-control" name="brand" required>
            </div>

            <div class="mb-3">
                <label class="form-label">상품명</label>
                <input type="text" class="form-control" name="prodName" required>
            </div>

            <div class="mb-3">
                <label class="form-label">가격</label>
                <input type="number" class="form-control" name="prodPrice" required>
            </div>

<%--            <div class="mb-3">--%>
<%--                <label class="form-label">상품 코드</label>--%>
<%--                <input type="number" class="form-control" name="prodCode" required>--%>
<%--            </div>--%>

            <div class="mb-3">
                <label class="form-label">상품 크기 (cm³)</label>
                <input type="number" step="any" class="form-control" name="prodSize" required>
            </div>

            <div class="mb-3">
                <label class="form-label">대분류</label>
                <select id="category-level1" name="categoryLevel1" class="form-select" required></select>
            </div>

            <div class="mb-3">
                <label class="form-label">중분류</label>
                <select id="category-level2" name="categoryLevel2" class="form-select" required></select>
            </div>

            <div class="mb-3">
                <label class="form-label">소분류</label>
                <select id="category-level3" name="categoryLevel3" class="form-select" required></select>
            </div>

            <div class="text-center mt-4">
                <button type="submit" class="btn btn-primary px-5 py-2 fw-semibold">등록</button>
            </div>
        </form>
    </div>
</div>



<script>
    const categoryMap = JSON.parse('<c:out value="${categoryJson}" escapeXml="false"/>');

    const level1Select = document.getElementById("category-level1");
    const level2Select = document.getElementById("category-level2");
    const level3Select = document.getElementById("category-level3");

    function clearSelect(select) {
        select.innerHTML = '';
        const defaultOption = document.createElement("option");
        defaultOption.textContent = '선택';
        defaultOption.disabled = true;
        defaultOption.selected = true;
        select.appendChild(defaultOption);
    }

    function fillSelect(select, options) {
        clearSelect(select);
        options.forEach(opt => {
            const option = document.createElement("option");
            option.value = opt;
            option.textContent = opt;
            select.appendChild(option);
        });
    }

    document.addEventListener("DOMContentLoaded", () => {
        const level1List = Object.keys(categoryMap);
        fillSelect(level1Select, level1List);

        level1Select.addEventListener("change", () => {
            const selectedLevel1 = level1Select.value;
            const level2List = Object.keys(categoryMap[selectedLevel1]);
            fillSelect(level2Select, level2List);
            clearSelect(level3Select); // 중분류 변경되면 소분류 초기화
        });

        level2Select.addEventListener("change", () => {
            const selectedLevel1 = level1Select.value;
            const selectedLevel2 = level2Select.value;
            const level3List = categoryMap[selectedLevel1][selectedLevel2];
            fillSelect(level3Select, level3List);
        });

        // 초기화 트리거
        level1Select.dispatchEvent(new Event("change"));
    });
</script>

