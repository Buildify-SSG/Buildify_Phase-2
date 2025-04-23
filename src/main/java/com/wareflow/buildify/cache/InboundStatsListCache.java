package com.wareflow.buildify.cache;

import com.wareflow.buildify.dto.InboundStatsDTO;

import java.util.List;

public class InboundStatsListCache {

    private static final InboundStatsListCache instance = new InboundStatsListCache();
    private List<InboundStatsDTO> inboundStatsDTOList;

    private InboundStatsListCache() {
    }

    public static InboundStatsListCache getInstance() {
        if (instance == null) {
            throw new IllegalStateException("List is not initialized. Call getInstance(List<InboundStatsDTO>) first.");
        }
        return instance;
    }

    public List<InboundStatsDTO> getInboundStatsDTOList() {
        return inboundStatsDTOList;
    }

    public void setInboundStatsDTOList(List<InboundStatsDTO> inboundStatsDTOList) {
        this.inboundStatsDTOList = inboundStatsDTOList;
    }
}
