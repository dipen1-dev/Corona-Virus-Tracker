package com.example.coronacasestracker.controllers;


import com.example.coronacasestracker.models.LocationStats;
import com.example.coronacasestracker.services.CoronaTrackerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private CoronaTrackerServices coronaTrackerServices;


    public HomeController(CoronaTrackerServices coronaTrackerServices) {
        this.coronaTrackerServices = coronaTrackerServices;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<LocationStats> allStats = coronaTrackerServices.getAllStats();
        int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getTotal_cases()).sum();
        int totalNewCases = allStats.stream().mapToInt(stat -> stat.getNew_cases()).sum();
        model.addAttribute("locationStats", allStats);
        model.addAttribute("totalReportedCases", totalReportedCases);
        model.addAttribute("totalNewCases", totalNewCases);

        return "home";
    }


}

