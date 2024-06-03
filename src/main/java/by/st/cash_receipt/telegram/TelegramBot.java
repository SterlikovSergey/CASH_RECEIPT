package by.st.cash_receipt.telegram;

import by.st.cash_receipt.config.BotProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@RequiredArgsConstructor
@Slf4j
public class TelegramBot extends TelegramLongPollingBot {
    private final BotProperties botProperties;
    @Override
    public void onUpdateReceived(Update update) {
            if (update.hasCallbackQuery()) {
                CallbackQuery callbackQuery = update.getCallbackQuery();
                String data = callbackQuery.getData(); // Получаем данные в формате строки JSON

                try {
                    ObjectMapper mapper = new ObjectMapper();
                    JsonNode dataNode = mapper.readTree(data); // Преобразуем строку JSON в JsonNode
                    // Теперь вы можете обрабатывать данные JsonNode
                    log.info("Received data: {}", dataNode);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }



    private void sendMessage(String chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);
        try {
            execute(message); // Вызов метода для отправки сообщения
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    @Override
    public String getBotUsername() {
        return botProperties.getName();
    }

    @Override
    public String getBotToken() {
        return botProperties.getToken();
    }
}
