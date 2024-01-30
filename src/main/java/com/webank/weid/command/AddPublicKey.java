package com.webank.weid.command;


import com.webank.weid.constant.ErrorCode;
import com.webank.weid.protocol.base.WeIdPrivateKey;
import com.webank.weid.protocol.request.PublicKeyArgs;
import com.webank.weid.protocol.response.ResponseData;
import com.webank.weid.rpc.WeIdService;
import com.webank.weid.service.impl.WeIdServiceImpl;
import com.webank.weid.util.WeIdUtils;
import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.web3j.crypto.ECKeyPair;
import org.fisco.bcos.web3j.crypto.gm.GenCredential;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
public class AddPublicKey {

    private static final Logger logger = LoggerFactory.getLogger(WeIdServiceImpl.class);

    public static void main(String[] args) {

        //log提示
        log.info("[AddPublicKey] begin to add public key.");

        //add 添加的公钥 与私钥无关，现阶段不知道有什么用
        String privateKey = "100508023513665587923134719470166278349164955702135880330126213803839270695664";
        //String privateKey = "45069077182475169894006664709295531485470925856363044466168623348872903368088";

        //判断private key 长度是否合法(是否超出最大值)
        if(!WeIdUtils.isPrivateKeyLengthValid(privateKey)){
            System.out.println("[AddPublicKey] private key is not length valid" );
            System.exit(1);
        }
        //设置私钥
        WeIdPrivateKey weIdPrivateKey = new WeIdPrivateKey();
        weIdPrivateKey.setPrivateKey(privateKey);
        //判断私钥是否非空
        if (!WeIdUtils.isPrivateKeyValid(weIdPrivateKey)){
            System.out.println("[AddPublicKey] private key is not valid" );
            System.exit(1);
        }


        WeIdService weIdService = new WeIdServiceImpl();


        //获得private key 对应的weid
        // String testWeId = "did:weid:1:0xa8c5d0b6336755882582402b24e21ebe810320da";
        String testWeId = WeIdUtils.getWeIdFromPrivateKey(privateKey);

        //判断获取的weid是否存在
        ResponseData<Boolean> responseIsExist =  weIdService.isWeIdExist(testWeId);
        if(!responseIsExist.getResult()){
            System.out.println("[AddPublicKey] get WeId failed, there is no such WeId on chain, result: " + responseIsExist.getErrorMessage());
            System.exit(1);
        }else {
            System.out.println("[AddPublicKey] get WeId successfully");
        }
        System.out.println(testWeId);

        //创建新的公钥
        //建议加入指定公钥
        //String newPublicKey = "";
        //以下为随便生成的公钥
        ECKeyPair newPair = GenCredential.createKeyPair(privateKey);
        String newPublicKey = String.valueOf(newPair.getPublicKey());

        System.out.println("------new public key-------");
        System.out.println(newPublicKey);

        //设置公钥
        PublicKeyArgs newPublicKeyArgs = new PublicKeyArgs();
        newPublicKeyArgs.setPublicKey(newPublicKey);

        //添加公私钥
        ResponseData<Integer> responseData =  weIdService.addPublicKey(testWeId,newPublicKeyArgs,weIdPrivateKey);
        //输出错误信息
        if (responseData.getErrorCode() != ErrorCode.SUCCESS.getCode()) {
            System.out.println("[AddPublicKey] add public key failed, result: " + responseData.getErrorMessage());
            System.exit(1);
        }

        //Show json of weid document
        ResponseData<String> responseJson = weIdService.getWeIdDocumentJson(testWeId);
        System.out.println("-----------------------------");
        System.out.println(responseJson.getResult());
        System.out.println("-----------------------------");

        System.exit(0);
    }
}

