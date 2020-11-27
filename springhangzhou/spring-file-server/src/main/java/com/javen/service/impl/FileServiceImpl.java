package com.javen.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.javen.service.FileService;
import org.springframework.stereotype.Service;

@Service
public class FileServiceImpl implements FileService<JSONObject> {
    public JSONObject upload() {
        return null;
    }

    public JSONObject download() {
        JSONObject object = new JSONObject();
        object.put("name", "jinwei");
        object.put("birthday", "19990101");
        object.put("address", "shanxixianyang");
        object.put("job", "enggenier");
        return object;
    }
}
