package org.zongf.test.tools.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.zongf.test.tools.vo.UserVO;

/** httpUtil 工具类测试
 * @author zongf
 * @date 2020-04-28
 */
@RestController
@RequestMapping("/httpUtil")
public class HttpUtilController {


    @GetMapping("/get")
    public String get(String name, Integer age) {
        return "[get] hello, " + name + ":" + age;
    }

    @PostMapping("/postForm")
    public String postForm(String name, Integer age) {
        return "[postForm] hello, " + name + ":" + age;
    }

    @PostMapping("/postJson")
    public String postJson(@RequestBody UserVO userVO) {
        return "[postJson] hello, " + userVO;
    }

    @PostMapping("/postFile")
    public String postFile(MultipartFile[] file, String name, String age) {
        StringBuffer sb = new StringBuffer();
        for (MultipartFile multipartFile : file) {
            sb.append(multipartFile.getName() + ":" + multipartFile.getOriginalFilename() + "\n");
        }
        sb.append("name:" + name).append("age:" + age);
        return "[postFile]" + sb.toString();
    }

}
