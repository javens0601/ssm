package com.javen.datastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MainTest {
    public static void main(String[] args) {
        //CheckTool.check("jinwei", () -> System.out.println("hello world"));
        List<AddFlowInfoRequest> addFlowInfoRequests = new ArrayList<>();
        List<AuditClassify> auditClassifyList = new ArrayList<>();
        AddFlowInfoRequest addFlowInfoRequest = new AddFlowInfoRequest();

        AuditClassify auditClassify0 = new AuditClassify();
        auditClassify0.setApproveNum(0);

        AuditClassify auditClassify1 = new AuditClassify();
        auditClassify1.setApproveNum(0);

        AuditClassify auditClassify2 = new AuditClassify();
        auditClassify2.setApproveNum(0);

        AuditClassify auditClassify3 = new AuditClassify();
        auditClassify3.setApproveNum(0);

        AuditClassify auditClassify4 = new AuditClassify();
        auditClassify4.setApproveNum(0);

        auditClassifyList.add(auditClassify0);
        auditClassifyList.add(auditClassify1);
        auditClassifyList.add(auditClassify2);
        auditClassifyList.add(auditClassify3);
        auditClassifyList.add(auditClassify4);
        addFlowInfoRequest.setAuditClassifyList(auditClassifyList);
        addFlowInfoRequests.add(addFlowInfoRequest);

        boolean f = isAuthMode(addFlowInfoRequests);
        System.out.println(f);


    }

    private static boolean  isAuthMode(List<AddFlowInfoRequest> addFlowInfoRequests) {
        return !addFlowInfoRequests.stream().anyMatch(item -> item.getAuditClassifyList().stream().anyMatch(classInfo -> !classInfo.getApproveNum().equals(0)));
    }

}

class AddFlowInfoRequest {
    private List<AuditClassify> auditClassifyList;

    public List<AuditClassify> getAuditClassifyList() {
        return auditClassifyList;
    }

    public void setAuditClassifyList(List<AuditClassify> auditClassifyList) {
        this.auditClassifyList = auditClassifyList;
    }
}

class AuditClassify {
    private Integer approveNum;

    public Integer getApproveNum() {
        return approveNum;
    }

    public void setApproveNum(Integer approveNum) {
        this.approveNum = approveNum;
    }
}
