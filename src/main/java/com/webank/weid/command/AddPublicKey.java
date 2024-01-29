package com.webank.weid.command;


import com.webank.weid.constant.ErrorCode;
import com.webank.weid.protocol.base.WeIdPrivateKey;
import com.webank.weid.protocol.base.WeIdPublicKey;
import com.webank.weid.protocol.request.PublicKeyArgs;
import com.webank.weid.protocol.response.ResponseData;
import com.webank.weid.rpc.WeIdService;
import com.webank.weid.service.impl.WeIdServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.web3j.crypto.ECKeyPair;
import org.fisco.bcos.web3j.crypto.gm.GenCredential;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
public class AddPublicKey {

    private static final Logger logger = LoggerFactory.getLogger(WeIdServiceImpl.class);
    public static void main(String[] args) {

        String testWeId = "did:weid:1:0xa8c5d0b6336755882582402b24e21ebe810320da";
        String privateKey = "100508023513665587923134719470166278349164955702135880330126213803839270695664";

        //log提示
        log.info("[AddPublicKey] begin to show WeId.");

        WeIdService weIdService = new WeIdServiceImpl();

        //获得private key 对应的weid


        //创建新的公钥
        ECKeyPair newPair = GenCredential.createKeyPair(privateKey);
        String newPublicKey = String.valueOf(newPair.getPublicKey());

        System.out.println("------new public key-------");
        System.out.println(newPublicKey);


        //设置公钥
        PublicKeyArgs newPublicKeyArgs = new PublicKeyArgs();
        newPublicKeyArgs.setPublicKey(newPublicKey);

        //设置私钥
        WeIdPrivateKey weIdPrivateKey = new WeIdPrivateKey();
        weIdPrivateKey.setPrivateKey(privateKey);

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
        System.out.println(responseJson);


        System.exit(0);
    }
}

