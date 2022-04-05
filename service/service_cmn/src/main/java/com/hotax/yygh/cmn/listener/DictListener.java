package com.hotax.yygh.cmn.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.hotax.yygh.cmn.mapper.DictMapper;
import org.springframework.beans.BeanUtils;
import yygh.model.cmn.Dict;
import yygh.vo.cmn.DictEeVo;

/**
 * @author: Jeff 2022-04-04 15:05
 * @description:
 *
 * DictMapper 注入的方式
 * 1. DictListener添加注解，交给spring容器管理
 * 2. 通过有参的构造将DictMapper传入
 *
 **/
public class DictListener extends AnalysisEventListener<DictEeVo> {

    private DictMapper dictMapper;
    public DictListener(DictMapper dictMapper) {
        this.dictMapper = dictMapper;
    }

    @Override
    public void invoke(DictEeVo dictEeVo, AnalysisContext analysisContext) {
        Dict dict = new Dict();
        BeanUtils.copyProperties(dictEeVo, dict, Dict.class);
        dictMapper.insert(dict);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
