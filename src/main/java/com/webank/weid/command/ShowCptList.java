package com.webank.weid.command;

import com.beust.jcommander.JCommander;
import com.webank.weid.constant.ErrorCode;
import com.webank.weid.dto.CptInfo;
import com.webank.weid.dto.Issuer;
import com.webank.weid.dto.PageDto;
import com.webank.weid.protocol.base.AuthorityIssuer;
import com.webank.weid.protocol.response.ResponseData;
import com.webank.weid.rpc.AuthorityIssuerService;
import com.webank.weid.service.WeIdSdkService;
import com.webank.weid.service.impl.AuthorityIssuerServiceImpl;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
@Slf4j
public class ShowCptList {
    private static WeIdSdkService weIdSdkService = new WeIdSdkService();
    public static void main(String[] args) {

        if (args == null || args.length < 6) {
            log.error(
                    "[ShowCptList] input parameters error, please check your input!");
            System.exit(1);
        }

        CommandArgs commandArgs = new CommandArgs();
        JCommander.newBuilder()
                .addObject(commandArgs)
                .build()
                .parse(args);

        //log提示
        log.info("[ShowCptList] begin to show cpt.");

        //获取index num cptType
        int index = commandArgs.getIndex();
        int num = commandArgs.getNum();
        String cptType = commandArgs.getType();

        PageDto<CptInfo> pageDto = new PageDto<>(index,num);
        pageDto.setQuery(new CptInfo());
        pageDto.getQuery().setCptType(cptType);

        //展示所有发行方列表
        ResponseData<PageDto<CptInfo>> response = weIdSdkService.getCptList(pageDto);

        //输出信息
        if (response.getErrorCode() != ErrorCode.SUCCESS.getCode()) {
            System.out.println("[ShowCptList] Show cpt list failed, result: " + response.getErrorMessage());
            System.exit(1);
        }

        System.out.println("-----------------------------");
        System.out.println(response.getResult().getDataList());
        System.out.println("-----------------------------");

        System.exit(0);


    }
}
