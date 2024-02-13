package com.webank.weid.command;

import com.webank.weid.constant.ErrorCode;
import com.webank.weid.protocol.base.AuthorityIssuer;
import com.webank.weid.protocol.response.ResponseData;
import com.webank.weid.rpc.AuthorityIssuerService;
import com.webank.weid.service.impl.AuthorityIssuerServiceImpl;

import java.util.List;

public class ShowIssuerList {
    public static AuthorityIssuerService authorityIssuerService = new AuthorityIssuerServiceImpl();
    public static void main(String[] args) {
        //展示所有发行方列表

        ResponseData<List<AuthorityIssuer>> response = authorityIssuerService.getAllAuthorityIssuerList(0, 2);

        //输出信息
        if (response.getErrorCode() != ErrorCode.SUCCESS.getCode()) {
            System.out.println("[Show WeId] Show WeId failed, result: " + response.getErrorMessage());
            System.exit(1);
        }

        System.out.println("-----------------------------");
        System.out.println(response.getResult());
        System.out.println("-----------------------------");

        System.exit(0);


    }
}
