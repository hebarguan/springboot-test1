package com.vm.test.config;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author huaihai.guan
 * @since 2021/8/23 12:27
 */
public class ErrorView extends FastJsonJsonView {

    private JsonResponse jsonResponse;

    public ErrorView(JsonResponse jsonResponse) {
        this.jsonResponse = jsonResponse;
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.getOutputStream().write(this.jsonResponse.toString().getBytes(StandardCharsets.UTF_8));
    }

    @Override
    protected void prepareResponse(HttpServletRequest request, HttpServletResponse response) {
        super.prepareResponse(request, response);
    }
}
