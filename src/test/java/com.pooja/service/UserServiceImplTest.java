package com.pooja.service;

import com.pooja.entity.User;
import com.pooja.repository.UserRepository;
import com.pooja.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static jdk.internal.org.objectweb.asm.util.CheckClassAdapter.verify;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnUser_whenIdExists() {
        // Arrange
        User mockUser = new User();
        mockUser.setId("123");
        mockUser.setUserName("John");

        when(userRepository.findById("123")).thenReturn(Optional.of(mockUser));

        // Act
        Optional<com.pooja.model.User> result = userService.getUserById("123");

        // Assert
        assertEquals("123", result.get().getId());
        assertEquals("John", result.get().getUsername());
    }

    @Test
    void shouldUpdateUser_whenIdExists() {
        // Arrange
        com.pooja.model.User mockUser = new com.pooja.model.User();
        mockUser.setId("123");
        mockUser.setUsername("John");
        mockUser.setAge(23);

        when(userRepository.existsById("123")).thenReturn(true);
        when(userRepository.save(any())).thenReturn(null); // We don't care about actual DB return here

        // Act
        com.pooja.model.User result = userService.updateUser("123", mockUser);

        // Assert
        assertNotNull(result);
        assertEquals("John", result.getUsername());
        verify(userRepository,times(1)).save(any());
    }

    @Test
    void shouldReturnNull_whenIdDoesNotExist() {
        // Arrange
        com.pooja.model.User mockUser = new com.pooja.model.User();
        mockUser.setId("123");
        mockUser.setUsername("John");

        when(userRepository.existsById("123")).thenReturn(false);

        // Act
        com.pooja.model.User result = userService.updateUser("123", mockUser);

        // Assert
        assertNull(result);
        verify(userRepository, never()).save(any());
    }

    @Test
    void shouldFailOnDeletion_whenUserDoesNotExist(){
        userService.deleteUser("sds");
    }

//    shouldFail
}

