package se.hiq.boardgamesbackend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class CommitInfoController {

    @Value("${git.commit.message.short}")
    private String commitMessage;

    @Value("${git.build.time}")
    private String buildTime;

    @Value("${git.commit.id}")
    private String commitId;

    @GetMapping("/commitId")
    public @ResponseBody Map<String, String> getCommitId() {
        Map<String, String> result = new HashMap<>();
        result.put("commit_message",commitMessage);
        result.put("commit_time", buildTime);
        result.put("commit_id", commitId);
        return result;
    }
}