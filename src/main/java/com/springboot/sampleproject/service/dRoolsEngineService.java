package com.springboot.sampleproject.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.DroolsEngine.DroolsApp;
import org.DroolsEngine.DroolsEngineInput;
import org.springframework.core.task.TaskRejectedException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

// dRoolsEngine 비동기 처리 용
@Slf4j
@Service
@RequiredArgsConstructor
public class dRoolsEngineService {
    @Async
    public void asyncMethod(int i) {
        try {
            Thread.sleep(500);
            log.info("[AsyncMethod]" + "-" + i + " " +  drools(10241.093046189342,734.6519734283407,2));
        } catch (TaskRejectedException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    public String drools(double posX, double posY, int poseName) {

        String ruleFilePath = "D:\\drools\\inference-rules-total.xlsx";

        List<DroolsEngineInput> input = new ArrayList<>();
        DroolsEngineInput dd = new DroolsEngineInput();

//        dd.setPositionX(10241.093046189342);
//        dd.setPositionY(734.6519734283407);
//        dd.setPoseName(2);

        dd.setPositionX(posX);
        dd.setPositionY(posY);
        dd.setPoseName(poseName);

        input.add(dd);

        List<DroolsEngineInput> out = DroolsApp.ExecuteEngine(input, ruleFilePath);

        for (int i = 0; i < out.size(); i++) {
            System.out.println("Inference out: " + i +
                    " / x: " + out.get(i).positionX +
                    " / y: " + out.get(i).positionY +
                    " / LN: " + out.get(i).locationName +
                    " / LT: " + out.get(i).locationType +
                    " / TN: " + out.get(i).taskName +
                    " / TT: " + out.get(i).taskType +
                    " / P: " + out.get(i).poseName +
                    " / w1: " + out.get(i).locationNtaskWeight +
                    " / w2: " + out.get(i).poseWeight +
                    " / rst: " + out.get(i).getResultOfCalc() +
                    " / Lvl: " +out.get(i).getRiskLevel()
            );
        }

        return out.get(0).getRiskLevel();
    }
}
