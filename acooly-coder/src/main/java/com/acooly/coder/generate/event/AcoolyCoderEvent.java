/**
 * acooly-coder
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2020-06-10 01:43
 */
package com.acooly.coder.generate.event;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

/**
 * @author zhangpu
 * @date 2020-06-10 01:43
 */
public class AcoolyCoderEvent {

    /**
     * 本次生成的所有表
     */
    private List<String> tables;

    /**
     * 当前生成的表
     */
    private String table;

    /**
     * 本次生成的所有步骤
     */
    private List<String> steps;

    /**
     * 当前步骤
     */
    private String step;

    public List<String> getTables() {
        return tables;
    }

    public void setTables(List<String> tables) {
        this.tables = tables;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public List<String> getSteps() {
        return steps;
    }

    public void setSteps(List<String> steps) {
        this.steps = steps;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public Pair<String, Double> getProgress() {
        int tableIndex = this.tables.indexOf(this.table) + 1;
        int stepIndex = this.steps.indexOf(this.step) + 1;
        double fraction = (tableIndex * stepIndex * 1.0d) / (this.tables.size() * this.steps.size());
        String text = this.table + " > 生成: " + this.step;
        return Pair.of(text, fraction);
    }


}
