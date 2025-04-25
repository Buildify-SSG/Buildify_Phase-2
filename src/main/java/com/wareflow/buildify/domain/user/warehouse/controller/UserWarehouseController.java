package com.wareflow.buildify.domain.user.warehouse.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wareflow.buildify.domain.admin.systemOperation.service.AdminWarehouseService;
import com.wareflow.buildify.domain.auth.login.security.CustomUserDetails;
import com.wareflow.buildify.domain.user.warehouse.service.UserWarehouseService;
import com.wareflow.buildify.dto.ProductDTO;
import com.wareflow.buildify.dto.UserWareHouseDTO;
import com.wareflow.buildify.dto.WareHouseDTO;
import com.wareflow.buildify.dto.WarehouseViewDTO;
import com.wareflow.buildify.util.Pagination;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/users/pages")
public class UserWarehouseController {

    private final AdminWarehouseService adminWarehouseService;
    private final UserWarehouseService userWarehouseService;

    @GetMapping("/userWarehouse/userWarehouse-1")
    public String getWarehouseList(Model model) throws JsonProcessingException {
        Map<String , Map<String, List<WarehouseViewDTO>>> layoutmap = adminWarehouseService.getWarehouseList();
        log.info("layout toString() :" + layoutmap.toString());
        log.info("컨트롤러 map 사이즈 : "+layoutmap.size());
        model.addAttribute("body","/WEB-INF/views/users/pages/userWarehouse/userWarehouse-1.jsp");
        model.addAttribute("map", layoutmap);

        List<WareHouseDTO> wareHouseDTOList = adminWarehouseService.getWarehouseInfo();
        log.info("wareHouseDTOList toString() :" + wareHouseDTOList.toString());
        model.addAttribute("wareInfo", wareHouseDTOList);

        ObjectMapper mapper = new ObjectMapper();
        String mapJson = mapper.writeValueAsString(layoutmap);
        model.addAttribute("mapJson", mapJson);

        return "users/layouts/userlayout";
    }

    @PostMapping("/userWarehouse/userWarehouse-1")
    public String submitWarehouse(
            @RequestParam("wareId") String wareId,
            @RequestParam("selectedCoords") List<String> selectedCoords,
            @RequestParam("rentalMonths") int rentalMonths,
            RedirectAttributes rttr) {

        log.info("🟡 선택된 창고: {}", wareId);
        log.info("🟢 선택된 좌표들: {}", selectedCoords);

        // 🔽 인증 정보 수동 획득
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();

        if (userDetails == null) {
            log.error("❌ userDetails가 null입니다.");
        } else {
            log.info("✅ userDetails 객체 타입: {}", userDetails.getClass().getName());
            log.info("✅ userDetails.getUsername(): {}", userDetails.getUsername());
            log.info("✅ userDetails.getClientId(): {}", userDetails.getClientId());
        }

        boolean allSuccess = true;

        for (String coord : selectedCoords) {
            UserWareHouseDTO dto = new UserWareHouseDTO();
            dto.setWareId(wareId);
            dto.setClientId(userDetails.getClientId());
            dto.setWarehousePosX(coord.substring(0, 1));   // 예: "A"
            dto.setWarehousePosY(Integer.parseInt(coord.substring(1))); // 예: 1
            dto.setWarehouseUsage(BigDecimal.valueOf(0));
            dto.setContractArea(BigDecimal.valueOf(100));
            dto.setWareStartDate(new java.util.Date());

            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MONTH, rentalMonths); // ✅ 사용자가 선택한 개월 수 반영
            dto.setWareEndDate(cal.getTime());

            if (!userWarehouseService.registerWarehouse(dto)) {
                allSuccess = false;
                break;
            }
        }

        rttr.addFlashAttribute("msg", allSuccess ? "신청 완료" : "신청 실패");

        //클라이언트 id 기반으로 user 테이블의 status = > 1 로 변경.
        return "redirect:/users/pages/userWarehouse/userWarehouse-1?wareId=" + wareId;
    }

    @GetMapping("/userWarehouse/userWarehouse-2")
    public String getMyWarehouse(@RequestParam(defaultValue = "1") int page, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        List<UserWareHouseDTO> myWarehouses = userWarehouseService.getMyWarehouse(userDetails.getClientId());
        log.info("내 창고 신청 내역: " + myWarehouses);
        model.addAttribute("myWarehouses", myWarehouses);

        // ✨ layout에서 include할 JSP 경로 지정!
        model.addAttribute("body", "/WEB-INF/views/users/pages/userWarehouse/userWarehouse-2.jsp");
        Pagination.paginate(model, myWarehouses, page,"/WEB-INF/views/users/pages/userWarehouse/userWarehouse-2.jsp");
        return "users/layouts/userlayout";
    }

}
