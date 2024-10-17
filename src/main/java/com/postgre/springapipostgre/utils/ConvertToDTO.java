package com.postgre.springapipostgre.utils;

import com.postgre.springapipostgre.DTO.base.MouNdaDTO;
import com.postgre.springapipostgre.DTO.base.PKSDTO;
import com.postgre.springapipostgre.models.MouNda;
import com.postgre.springapipostgre.models.PKS;

import java.util.List;
import java.util.stream.Collectors;

public class ConvertToDTO {
    public static List<MouNdaDTO> toMouNdaDTO(List<MouNda> mouNdaList) {
        return mouNdaList.stream().map(mouNda -> {
            MouNdaDTO dto = new MouNdaDTO();
            dto.setId(mouNda.getId());
            dto.setPartnershipTitle(mouNda.getPartnershipTitle());
            dto.setBackground(mouNda.getBackground());
            dto.setNote(mouNda.getNote());
            dto.setPartnershipCandidate(mouNda.getPartnershipCandidate());
            dto.setStatus(mouNda.getStatus().toString());

            return dto;
        }).collect(Collectors.toList());
    }

    public static List<PKSDTO> toPksDTO(List<PKS> pksList) {
        return pksList.stream().map(pks -> {
            PKSDTO dto = new PKSDTO();
            dto.setId(pks.getId());
            dto.setTitle(pks.getTitle());
            dto.setBackground(pks.getBackground());
            dto.setNote(pks.getNote());
            dto.setPartnershipCandidate(pks.getPartnershipCandidate());
            dto.setStatus(pks.getStatus().toString());

            return dto;
        }).collect(Collectors.toList());
    }

}
