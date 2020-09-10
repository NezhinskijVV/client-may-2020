package ru.itsjava.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Класс MessageInputService: ")
public class MessageInputServiceTest {

    @DisplayName(" должен корректно читать сообщение из потока")
    @Test
    public void shouldHaveCorrectReadMessage() {
        MessageInputService messageInputService = new MessageInputService(
                new ByteArrayInputStream(new byte[]{12, 44, 22}));

       assertEquals((char) 12, messageInputService.readMessage().charAt(0));
    }
}


