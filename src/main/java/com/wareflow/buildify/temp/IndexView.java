package com.wareflow.buildify.temp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexView {

    @GetMapping("/login")
    public String index() { return "login"; }

    @GetMapping("/components/charts-chartjs")
    public String chartsChartjs() { return "components/charts-chartjs"; }

    @GetMapping("/components/icons-feather")
    public String iconsFeather() { return "components/icons-feather"; }

    @GetMapping("/components/maps-google")
    public String mapsGoogle() { return "components/maps-google"; }

    @GetMapping("/components/pages-blank")
    public String pagesBlank() { return "components/pages-blank"; }

    @GetMapping("/components/pages-sign-in")
    public String pagesSignIn() { return "components/pages-sign-in"; }

//    @GetMapping("/components/pages-sign-up")
//    public String pagesSignUp() { return "components/pages-sign-up"; }

    @GetMapping("/components/ui-buttons")
    public String uiButtons() { return "components/ui-buttons"; }

    @GetMapping("/components/ui-cards")
    public String uiCards() { return "components/ui-cards"; }

    @GetMapping("/components/ui-forms")
    public String uiForms() { return "components/ui-forms"; }

    @GetMapping("/components/ui-typography")
    public String uiTypography() { return "components/ui-typography"; }

}
