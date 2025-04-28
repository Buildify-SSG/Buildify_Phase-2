package com.wareflow.buildify.domain.user.inbound.service;

import com.wareflow.buildify.domain.auth.login.security.CustomUserDetails;
import com.wareflow.buildify.domain.user.inbound.mapper.UserInboundMapper;
import com.wareflow.buildify.dto.InboundDTO;
import com.wareflow.buildify.dto.InboundProduntDTO;
import com.wareflow.buildify.dto.InboundRequestDTO;
import com.wareflow.buildify.dto.ProductDTO;
import com.wareflow.buildify.vo.InboundProductVO;
import com.wareflow.buildify.vo.InboundVO;
import com.wareflow.buildify.vo.ProductVO;
import com.wareflow.buildify.vo.UserWareHouseVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.ls.LSOutput;
import java.math.RoundingMode;


import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional

public class UserInboundServiceImpl implements UserInboundService {
    private final UserInboundMapper userInboundMapper;

    @Override
    public List<ProductDTO> inboundList() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        log.info("1111111111111111111");

        ProductVO VO = new ProductVO();
        VO.setClientId(userDetails.getClientId());
//
//        List<UserWareHouseVO> userWarehouses = userInboundMapper.insertware(VO);
//        log.info("rkwushsdhwl");
//        BigDecimal totalWareSize = BigDecimal.ZERO;
//        for (UserWareHouseVO warehouse : userWarehouses) {
//            BigDecimal usage =warehouse.getWarehouseUsage(); // int → BigDecimal
//            totalWareSize = totalWareSize.add(usage); // += 대신 add()
//        }
//
//        log.info(totalWareSize);
//
//
//
        log.info("인바운드1서비스");
        List<ProductVO> vo = userInboundMapper.inboundList(VO);

//        // 🔥 총 요청 사이즈 계산
//        BigDecimal requestedSize = BigDecimal.ZERO;
//        for (ProductVO productVO : vo) {
//            if (productVO.getProdSize() != null) {
//                requestedSize = requestedSize.add(productVO.getProdSize());
//            }
//        }
//
//        // 🔥 비교 후 조건 처리
//        if (requestedSize.compareTo(totalWareSize) > 0) {
//            log.warn("⛔ 창고 공간 초과: 사용 가능 = {}, 요청 = {}", totalWareSize, requestedSize);
//            return Collections.emptyList(); // 혹은 예외 발생
////             throw new IllegalStateException("창고 공간이 부족합니다.");
//        }

        List<ProductDTO> dtoList = new ArrayList<>();
        for (ProductVO productVO : vo) {
            ProductDTO productDTO = ProductDTO.builder()
                    .prodId(productVO.getProdId())
                    .prodName(productVO.getProdName())
                    .prodPrice(productVO.getProdPrice())
                    .prodSize(productVO.getProdSize())
                    .clientId(productVO.getClientId())
                    .build();
            dtoList.add(productDTO);

        }

        return dtoList;

    }

    @Override
    public List<InboundProduntDTO> inboundInsertlist() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();

        InboundProductVO VO = new InboundProductVO();
        VO.setClientId(userDetails.getClientId());

        List<InboundProductVO> vo = userInboundMapper.inboundInsertlist(VO);

        List<InboundProduntDTO> dtoList = new ArrayList<>();
        for (InboundProductVO inboundProductVO : vo) {
            InboundProduntDTO inboundProduntDTO = InboundProduntDTO.builder()
                    .clientId(inboundProductVO.getClientId())
                    .prodName(inboundProductVO.getProdName())
                    .quantity(inboundProductVO.getQuantity())
                    .reqInboundDate(inboundProductVO.getReqInboundDate())
                    .inboundProcessDate(inboundProductVO.getInboundProcessDate())
                    .inboundStatus(inboundProductVO.getInboundStatus())
                    .wareId(inboundProductVO.getWareId())
                    .build();
            dtoList.add(inboundProduntDTO);
        }
        return dtoList;
    }

    @Override
    public List<InboundVO> inboundInsert() {
        return null;
    }

    @Override
    public List<InboundProduntDTO> searchInboundList(String searchType, String keyword) {

        return userInboundMapper.searchInboundList(searchType, "%" + keyword + "%");
    }

    @Override
    public List<ProductDTO> searchInboundInsertList(String searchType, String keyword) {
        return userInboundMapper.searchInboundInsertList(searchType, "%" + keyword + "%");
    }

    @Override
    public List<ProductDTO> getInboundInsert(List<String> prodId) {
        return userInboundMapper.getInboundInsert(prodId);
    }


    public void insertInboundRequests(List<String> prodIds, List<Integer> quantities) {
        log.info("여기까진오니");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        String clientId = userDetails.getClientId();
        log.info("✅ userDetails 객체 타입: {}", userDetails.getClass().getName());
        log.info("✅ userDetails.getUsername(): {}", userDetails.getUsername());
        log.info("✅ userDetails.getClientId(): {}", userDetails.getClientId());

        List<UserWareHouseVO> userWarehouses = userInboundMapper.insertware(clientId);

        List<InboundVO> inboundVOList = new ArrayList<>();
        int warehouseIndex = 0;

        for (int i = 0; i < prodIds.size();i++){
            String prodId = prodIds.get(i);
            int remainingQty = quantities.get(i);

            BigDecimal prodSize = userInboundMapper.getProdSizeAsString(prodId); // 상품 크기

            if (prodSize == null) {
                log.warn("❌ 상품 크기 없음: {}", prodId);
                continue;
            }

            for (UserWareHouseVO warehouse : userWarehouses) {
                BigDecimal available = warehouse.getContractArea().subtract(warehouse.getWarehouseUsage());

                // 현재 창고에서 넣을 수 있는 최대 수량
                BigDecimal maxInsertable = available.divide(prodSize, RoundingMode.FLOOR);
                int insertQty = Math.min(maxInsertable.intValue(), remainingQty);

                if (insertQty <= 0) continue;

                // insert
                InboundVO vo = new InboundVO();
                vo.setInboundId("INB-" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")) + "-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
                vo.setProdId(prodId);
                vo.setClientId(clientId);
                vo.setQuantity(insertQty);
                vo.setWareId(warehouse.getWareId());
                vo.setWarehousePosX(warehouse.getWarehousePosX());
                vo.setWarehousePosY(warehouse.getWarehousePosY());
                vo.setInboundStatus(0);
                vo.setReqInboundDate(Date.valueOf(LocalDate.now()));

                userInboundMapper.insertInbound(vo);
                log.info("📦 창고 {}에 상품 {} 수량 {} 등록", warehouse.getWareId(), prodId, insertQty);

                remainingQty -= insertQty;

                if (remainingQty <= 0) break;
            }

            if (remainingQty > 0) {
                log.warn("⚠️ 상품 {} 수량 {}는 공간 부족으로 처리되지 못함", prodId, remainingQty);
            }
        }


//        BigDecimal totalAvailableWareSize = BigDecimal.ZERO;
//        for (UserWareHouseVO warehouse : userWarehouses) {
//            BigDecimal available = warehouse.getContractArea().subtract(warehouse.getWarehouseUsage());
//            totalAvailableWareSize = totalAvailableWareSize.add(available);
//        }
//
//        log.info("🧾 사용 가능 창고 공간: {}", totalAvailableWareSize);
//
//        // 👉 신청 제품 총 사이즈 구하기
//        BigDecimal requestedSize = BigDecimal.ZERO;
//        for (int i = 0; i < prodIds.size(); i++) {
//            String prodId = prodIds.get(i);
//            Integer qty = quantities.get(i);
//
//            // 상품 사이즈 가져오기
//            BigDecimal prodSize = userInboundMapper.getProdSizeAsString(prodId); // 예: "5.5"
//
////            BigDecimal prodSize = userInboundMapper.findProdSizeByProdId(prodId); // <== MyBatis 쿼리 필요
//
//            if (prodSize == null) {
//                log.warn("⛔ prodSize가 null입니다. prodId={}", prodId);
//                continue;
//            }
//
//            BigDecimal sizeTotal = prodSize.multiply(BigDecimal.valueOf(qty));
//            requestedSize = requestedSize.add(sizeTotal);
//        }
//
//        log.info("✅ 총 사용 가능한 창고 공간: {}", totalAvailableWareSize);
//        log.info("📦 요청한 총 상품 사이즈: {}", requestedSize);
//
//        // 👉 비교
//        if (requestedSize.compareTo(totalAvailableWareSize) > 0) {
//            log.warn("⛔ 창고 공간 초과! 요청 사이즈가 사용 가능한 공간보다 큽니다.");
//            throw new IllegalStateException("창고 공간이 부족합니다.");
//        }
//
//
//        //////////
//
//        for(int j =0; j< userWarehouses.size();j++){
//            UserWareHouseVO wareHouse = userWarehouses.get(j);
//            log.info("🚚 창고 ID: {}", wareHouse.getWareId());
//            log.info("📦 사용 공간: {}", wareHouse.getWarehouseUsage());
//            log.info("📏 위치 X: {}, Y: {}", wareHouse.getWarehousePosX(), wareHouse.getWarehousePosY());
//
//
//        }
//        for (int i = 0; i < prodIds.size(); i++) {
//            InboundVO vo = new InboundVO();
//            String uniqueId = "INB-" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"))
//                    + "-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
//
//            vo.setInboundId(uniqueId);
//            vo.setProdId(prodIds.get(i));
//            vo.setClientId(userDetails.getClientId());
//            vo.setQuantity(quantities.get(i));
//            vo.setInboundStatus(0); // 상태: 대기
//            vo.setReqInboundDate(Date.valueOf(LocalDate.now()));
//
//            int result = userInboundMapper.insertInbound(vo);
//            // 로그로 insert 결과 확인
//            log.info("🧾 insert 실행 결과: {} (inboundId={}, prodId={}, quantity={})",
//                    result, vo.getInboundId(), vo.getProdId(), vo.getQuantity());
//        }



    }
}