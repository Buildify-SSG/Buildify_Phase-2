package com.wareflow.buildify.cache;

import com.wareflow.buildify.dto.OutboundStatsDTO;

import java.util.List;

public class OutboundStatsListCache {

    private static final OutboundStatsListCache instance = new OutboundStatsListCache();
    private List<OutboundStatsDTO> outboundStatsDTOList;

    private OutboundStatsListCache() {
    }

    public static OutboundStatsListCache getInstance() {
        if (instance == null) {
            throw new IllegalStateException("List is not initialized. Call getInstance(List<OutboundStatsDTO>) first.");
        }
        return instance;
    }

    public List<OutboundStatsDTO> getOutboundStatsDTOList() {
        return outboundStatsDTOList;
    }

    public void setOutboundStatsDTOList(List<OutboundStatsDTO> outboundStatsDTOList) {
        this.outboundStatsDTOList = outboundStatsDTOList;
    }
}
