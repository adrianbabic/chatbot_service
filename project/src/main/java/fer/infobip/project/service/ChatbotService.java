package fer.infobip.project.service;

import fer.infobip.project.models.ChatbotResponse;
import fer.infobip.project.models.openAI.ChatGPTRequest;
import fer.infobip.project.models.openAI.ChatGPTResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ChatbotService {

    @Value("${openai.api.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiURL;

    @Autowired
    private RestTemplate template;

    public ChatbotResponse askChatbot(String prompt) {
        ChatGPTRequest req = new ChatGPTRequest(model, prompt);
        ChatGPTResponse resp = template.postForObject(apiURL, req, ChatGPTResponse.class);
        return new ChatbotResponse(resp.getChoices().get(0).getMessage().getContent());
    }

    //open_ai_adapter
}
