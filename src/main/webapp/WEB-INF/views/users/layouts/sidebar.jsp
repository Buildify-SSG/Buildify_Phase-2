<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <nav id="sidebar" class="sidebar js-sidebar">
        <div class="sidebar-content js-simplebar">
            <a class="sidebar-brand" href="<c:url value='/users/pages/index' />">
                <span class="align-middle">Buildify</span>
            </a>

            <ul class="sidebar-nav">
                <li class="sidebar-header">
                    Pages
                </li>

                <li class="sidebar-item active">
                    <a class="sidebar-link" href="<c:url value='/users/pages/index' />">
                        <i class="align-middle" data-feather="sliders"></i> <span class="align-middle">Dashboard</span>
                    </a>
                </li>

                <li class="sidebar-item">
                    <a class="sidebar-link collapsed  d-flex justify-content-between align-items-center" data-bs-target="#submenu-userWarehouse" data-bs-toggle="collapse" >
							<span>
								<i class="align-middle" data-feather="square"></i> <span class="align-middle">나의 창고</span>
							</span>
                        <i class="align-middle collapse-arrow" data-feather="chevron-down"></i>
                    </a>
                    <ul id="submenu-userWarehouse" class="sidebar-dropdown list-unstyled collapse ps-4" data-bs-parent="#sidebar">
                        <li class="sidebar-item">
                            <a class="sidebar-link" href="<c:url value='/users/pages/userWarehouse/userWarehouse-1' />">창고 신청</a>
                        </li>
                        <li class="sidebar-item">
                            <a class="sidebar-link" href="<c:url value='/users/pages/userWarehouse/userWarehouse-2' />">창고 현황</a>
                        </li>
<%--                        <li class="sidebar-item">--%>
<%--                            <a class="sidebar-link" href="<c:url value='/users/pages/userWarehouse/userWarehouse-3' />">계약 내역</a>--%>
<%--                        </li>--%>
<%--                        <li class="sidebar-item">--%>
<%--                            <a class="sidebar-link" href="<c:url value='/users/pages/userWarehouse/userWarehouse-4' />">유저 창고 추가 메뉴 2</a>--%>
<%--                        </li>--%>
                    </ul>
                </li>

                <li class="sidebar-item">
                    <a class="sidebar-link collapsed  d-flex justify-content-between align-items-center" data-bs-target="#submenu-product" data-bs-toggle="collapse" >
							<span>
								<i class="align-middle" data-feather="square"></i> <span class="align-middle">상품 관리</span>
							</span>
                        <i class="align-middle collapse-arrow" data-feather="chevron-down"></i>
                    </a>
                    <ul id="submenu-product" class="sidebar-dropdown list-unstyled collapse ps-4" data-bs-parent="#sidebar">
                        <li class="sidebar-item">
                            <a class="sidebar-link" href="<c:url value='/users/pages/product/product-1' />">상품 등록</a>
                        </li>
                        <li class="sidebar-item">
                            <a class="sidebar-link" href="<c:url value='/users/pages/product/product-2' />">상품 조회</a>
                        </li>
<%--                        <li class="sidebar-item">--%>
<%--                            <a class="sidebar-link" href="<c:url value='/users/pages/product/product-3' />">상품 추가 메뉴 1</a>--%>
<%--                        </li>--%>
<%--                        <li class="sidebar-item">--%>
<%--                            <a class="sidebar-link" href="<c:url value='/users/pages/product/product-4' />">상품 추가 메뉴 2</a>--%>
<%--                        </li>--%>
                    </ul>
                </li>

                <li class="sidebar-item">
                    <a class="sidebar-link collapsed d-flex justify-content-between align-items-center" data-bs-target="#submenu-inbound" data-bs-toggle="collapse">
							<span>
								<i class="align-middle" data-feather="square"></i> <span class="align-middle">입고 요청</span>
							</span>
                        <i class="align-middle collapse-arrow" data-feather="chevron-down"></i>
                    </a>
                    <ul id="submenu-inbound" class="sidebar-dropdown list-unstyled collapse ps-4" data-bs-parent="#sidebar">
                        <li class="sidebar-item">
                            <a class="sidebar-link" href="<c:url value='/users/pages/inbound/inbound-1' />">입고 요청</a>
                        </li>
                        <li class="sidebar-item">
                            <a class="sidebar-link" href="<c:url value='/users/pages/inbound/inbound-2' />">입고 현황 조회</a>
                        </li>
<%--                        <li class="sidebar-item">--%>
<%--                            <a class="sidebar-link" href="<c:url value='/users/pages/inbound/inbound-3' />">입고 추가 메뉴 1</a>--%>
<%--                        </li>--%>
<%--                        <li class="sidebar-item">--%>
<%--                            <a class="sidebar-link" href="<c:url value='/users/pages/inbound/inbound-4' />">입고 추가 메뉴 2</a>--%>
<%--                        </li>--%>
                    </ul>
                </li>

                <li class="sidebar-item">
                    <a class="sidebar-link collapsed d-flex justify-content-between align-items-center" data-bs-target="#submenu-outbound" data-bs-toggle="collapse">
							<span>
								<i class="align-middle" data-feather="square"></i> <span class="align-middle">출고 요청</span>
							</span>
                        <i class="align-middle collapse-arrow" data-feather="chevron-down"></i>
                    </a>
                    <ul id="submenu-outbound" class="sidebar-dropdown list-unstyled collapse ps-4" data-bs-parent="#sidebar">
                        <li class="sidebar-item">
                            <a class="sidebar-link" href="<c:url value='/users/pages/outbound/outbound-1' />">출고 요청</a>
                        </li>
                        <li class="sidebar-item">
                            <a class="sidebar-link" href="<c:url value='/users/pages/outbound/outbound-2' />">출고 현황 조회</a>
                        </li>
<%--                        <li class="sidebar-item">--%>
<%--                            <a class="sidebar-link" href="<c:url value='/users/pages/outbound/outbound-3' />">출고 추가 메뉴 1</a>--%>
<%--                        </li>--%>
<%--                        <li class="sidebar-item">--%>
<%--                            <a class="sidebar-link" href="<c:url value='/users/pages/outbound/outbound-4' />">출고 추가 메뉴 2</a>--%>
<%--                        </li>--%>
                    </ul>
                </li>

                <li class="sidebar-item">
                    <a class="sidebar-link collapsed d-flex justify-content-between align-items-center" data-bs-target="#submenu-inventory" data-bs-toggle="collapse">
							<span>
								<i class="align-middle" data-feather="square"></i> <span class="align-middle">재고 관리</span>
							</span>
                        <i class="align-middle collapse-arrow" data-feather="chevron-down"></i>
                    </a>
                    <ul id="submenu-inventory" class="sidebar-dropdown list-unstyled collapse ps-4" data-bs-parent="#sidebar">
                        <li class="sidebar-item">
                            <a class="sidebar-link" href="<c:url value='/users/pages/inventory/inventory-1' />">재고 조회</a>
                        </li>
<%--                        <li class="sidebar-item">--%>
<%--                            <a class="sidebar-link" href="<c:url value='/users/pages/inventory/inventory-2' />">재고 추가 메뉴 1</a>--%>
<%--                        </li>--%>
<%--                        <li class="sidebar-item">--%>
<%--                            <a class="sidebar-link" href="<c:url value='/users/pages/inventory/inventory-3' />">재고 추가 메뉴 2</a>--%>
<%--                        </li>--%>
<%--                        <li class="sidebar-item">--%>
<%--                            <a class="sidebar-link" href="<c:url value='/users/pages/inventory/inventory-4' />">재고 추가 메뉴 3</a>--%>
<%--                        </li>--%>
                    </ul>
                </li>


            </ul>

            <div class="sidebar-cta">
                <div class="sidebar-cta-content">
                    <strong class="d-inline-block mb-2"></strong>
                    <div class="mb-3 text-sm">
                    </div>
                    <div class="d-grid">
                        <img class="sidebar-img" src="<c:url value='/static/img/LOGO.png' />" alt="">
                    </div>
                </div>
            </div>
        </div>
    </nav>
