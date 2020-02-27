package life.majiang.community.community.controller;

import life.majiang.community.community.dto.AccesstokenDTO;
import life.majiang.community.community.dto.GithubUser;
import life.majiang.community.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state){
        AccesstokenDTO accesstokenDTO = new AccesstokenDTO();
        accesstokenDTO.setClient_id("25e93458be2ff6ffabf7");
        accesstokenDTO.setClient_secret("3383e762651c9d87c5a4f68cdbacd2072e91af7f");
        accesstokenDTO.setCode(code);
        accesstokenDTO.setRedirect_uri("http://localhost:8999/callback");
        accesstokenDTO.setState(state);
        String accesstoken = githubProvider.getAccesstoken(accesstokenDTO);
        GithubUser user = githubProvider.getUser(accesstoken);
        System.out.println(user.getName());
        return "index";
    }
}
