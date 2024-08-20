package by.st.cash_receipt.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WebhookController {
    @PostMapping("/webhook")
    @ResponseBody
    public String handleWebhook(@RequestBody String payload) throws JSONException {
        // Log the received payload
        System.out.println("Received payload: " + payload);

        // Parse the payload as JSON
        JSONObject jsonPayload = new JSONObject(payload);

        // Extract the message and selected items (assuming they are in the payload)
        if (jsonPayload.has("message")) {
            JSONObject message = jsonPayload.getJSONObject("message");
            // Add your logic to handle the message and selected items
            System.out.println("Message: " + message.toString());

            // Пример добавления логики обработки
            if (message.has("selectedItems")) {
                // Обработка выбранных элементов
                System.out.println("Selected Items: " + message.getJSONArray("selectedItems").toString());
                // Добавьте здесь вашу логику обработки выбранных элементов
            }
        }

        // Add your processing logic here

        return "Webhook processed";
    }
}
