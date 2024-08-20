package by.st.cash_receipt.telegram;

import by.st.cash_receipt.config.BotProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
@Component
@RequiredArgsConstructor
@Slf4j
public class MyWebhookBot extends TelegramWebhookBot {

    private final BotProperties botProperties;

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        try {
            if (update.hasMessage() && update.getMessage().hasText()) {
                return handleIncomingMessage(update);
            } else if (update.hasCallbackQuery()) {
                return handleCallbackQuery(update);
            }
        } catch (Exception e) {
            log.error("Error processing update: ", e);
        }
        return null;
    }

    private SendMessage handleIncomingMessage(Update update) {
        String messageText = update.getMessage().getText();
        long chatId = update.getMessage().getChatId();
        log.info("Received message: {} from chatId: {}", messageText, chatId);

        SendMessage response = new SendMessage();
        response.setChatId(String.valueOf(chatId));
        response.setText("Вы отправили сообщение: " + messageText);
        return response;
    }

    private SendMessage handleCallbackQuery(Update update) {
        String data = update.getCallbackQuery().getData();
        long chatId = update.getCallbackQuery().getMessage().getChatId();
        log.info("Received callback data: {} from chatId: {}", data, chatId);

        SendMessage response = new SendMessage();
        response.setChatId(String.valueOf(chatId));

        switch (data) {
            case "checkout":
                response.setText("Ваш заказ оформлен!");
                break;
            case "cancel":
                response.setText("Ваш заказ отменен.");
                break;
            default:
                response.setText("Получены данные: " + data);
                break;
        }
        return response;
    }

    @Override
    public String getBotPath() {
        return null;
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