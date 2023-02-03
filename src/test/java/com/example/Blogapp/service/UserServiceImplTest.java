package com.example.Blogapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.Blogapp.dao.UserRepo;
import com.example.Blogapp.entity.User;
import com.example.Blogapp.exceptions.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserServiceImpl.class})
@ExtendWith(SpringExtension.class)
class UserServiceImplTest {
    @MockBean
    private UserRepo userRepo;

    @Autowired
    private UserServiceImpl userServiceImpl;

    /**
     * Method under test: {@link UserServiceImpl#createUser(User)}
     */
    @Test
    void testCreateUser() {
        User user = new User();
        user.setAbout("About");
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPosts(new ArrayList<>());
        when(userRepo.save((User) any())).thenReturn(user);

        User user1 = new User();
        user1.setAbout("About");
        user1.setEmail("jane.doe@example.org");
        user1.setId(1);
        user1.setName("Name");
        user1.setPassword("iloveyou");
        user1.setPosts(new ArrayList<>());
        assertSame(user, userServiceImpl.createUser(user1));
        verify(userRepo).save((User) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#createUser(User)}
     */
    @Test
    void testCreateUser2() {
        when(userRepo.save((User) any())).thenThrow(new ResourceNotFoundException("Resource Name", "Field Name", 42L));

        User user = new User();
        user.setAbout("About");
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPosts(new ArrayList<>());
        assertThrows(ResourceNotFoundException.class, () -> userServiceImpl.createUser(user));
        verify(userRepo).save((User) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#updateUser(int, User)}
     */
    @Test
    void testUpdateUser() {
        User user = new User();
        user.setAbout("About");
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPosts(new ArrayList<>());
        Optional<User> ofResult = Optional.of(user);

        User user1 = new User();
        user1.setAbout("About");
        user1.setEmail("jane.doe@example.org");
        user1.setId(1);
        user1.setName("Name");
        user1.setPassword("iloveyou");
        user1.setPosts(new ArrayList<>());
        when(userRepo.save((User) any())).thenReturn(user1);
        when(userRepo.findById((Integer) any())).thenReturn(ofResult);

        User user2 = new User();
        user2.setAbout("About");
        user2.setEmail("jane.doe@example.org");
        user2.setId(1);
        user2.setName("Name");
        user2.setPassword("iloveyou");
        user2.setPosts(new ArrayList<>());
        User actualUpdateUserResult = userServiceImpl.updateUser(123, user2);
        assertSame(user, actualUpdateUserResult);
        assertEquals("About", actualUpdateUserResult.getAbout());
        assertEquals("iloveyou", actualUpdateUserResult.getPassword());
        assertEquals("Name", actualUpdateUserResult.getName());
        assertEquals("jane.doe@example.org", actualUpdateUserResult.getEmail());
        verify(userRepo).save((User) any());
        verify(userRepo).findById((Integer) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#updateUser(int, User)}
     */
    @Test
    void testUpdateUser2() {
        User user = new User();
        user.setAbout("About");
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPosts(new ArrayList<>());
        Optional<User> ofResult = Optional.of(user);
        when(userRepo.save((User) any())).thenThrow(new ResourceNotFoundException("Resource Name", "Field Name", 42L));
        when(userRepo.findById((Integer) any())).thenReturn(ofResult);

        User user1 = new User();
        user1.setAbout("About");
        user1.setEmail("jane.doe@example.org");
        user1.setId(1);
        user1.setName("Name");
        user1.setPassword("iloveyou");
        user1.setPosts(new ArrayList<>());
        assertThrows(ResourceNotFoundException.class, () -> userServiceImpl.updateUser(123, user1));
        verify(userRepo).save((User) any());
        verify(userRepo).findById((Integer) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#updateUser(int, User)}
     */
    @Test
    void testUpdateUser3() {
        User user = new User();
        user.setAbout("About");
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPosts(new ArrayList<>());
        when(userRepo.save((User) any())).thenReturn(user);
        when(userRepo.findById((Integer) any())).thenReturn(Optional.empty());

        User user1 = new User();
        user1.setAbout("About");
        user1.setEmail("jane.doe@example.org");
        user1.setId(1);
        user1.setName("Name");
        user1.setPassword("iloveyou");
        user1.setPosts(new ArrayList<>());
        assertThrows(ResourceNotFoundException.class, () -> userServiceImpl.updateUser(123, user1));
        verify(userRepo).findById((Integer) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#getUserById(int)}
     */
    @Test
    void testGetUserById() {
        User user = new User();
        user.setAbout("About");
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPosts(new ArrayList<>());
        Optional<User> ofResult = Optional.of(user);
        when(userRepo.findById((Integer) any())).thenReturn(ofResult);
        assertSame(user, userServiceImpl.getUserById(123));
        verify(userRepo).findById((Integer) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#getUserById(int)}
     */
    @Test
    void testGetUserById2() {
        when(userRepo.findById((Integer) any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> userServiceImpl.getUserById(123));
        verify(userRepo).findById((Integer) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#getUserById(int)}
     */
    @Test
    void testGetUserById3() {
        when(userRepo.findById((Integer) any())).thenThrow(new ResourceNotFoundException("User", "User", 42L));
        assertThrows(ResourceNotFoundException.class, () -> userServiceImpl.getUserById(123));
        verify(userRepo).findById((Integer) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#getAllUsers()}
     */
    @Test
    void testGetAllUsers() {
        ArrayList<User> userList = new ArrayList<>();
        when(userRepo.findAll()).thenReturn(userList);
        List<User> actualAllUsers = userServiceImpl.getAllUsers();
        assertSame(userList, actualAllUsers);
        assertTrue(actualAllUsers.isEmpty());
        verify(userRepo).findAll();
    }

    /**
     * Method under test: {@link UserServiceImpl#getAllUsers()}
     */
    @Test
    void testGetAllUsers2() {
        when(userRepo.findAll()).thenThrow(new ResourceNotFoundException("Resource Name", "Field Name", 42L));
        assertThrows(ResourceNotFoundException.class, () -> userServiceImpl.getAllUsers());
        verify(userRepo).findAll();
    }

    /**
     * Method under test: {@link UserServiceImpl#deleteUser(int)}
     */
    @Test
    void testDeleteUser() {
        doNothing().when(userRepo).deleteById((Integer) any());
        userServiceImpl.deleteUser(123);
        verify(userRepo).deleteById((Integer) any());
        assertTrue(userServiceImpl.getAllUsers().isEmpty());
    }

    /**
     * Method under test: {@link UserServiceImpl#deleteUser(int)}
     */
    @Test
    void testDeleteUser2() {
        doThrow(new ResourceNotFoundException("Resource Name", "Field Name", 42L)).when(userRepo)
                .deleteById((Integer) any());
        assertThrows(ResourceNotFoundException.class, () -> userServiceImpl.deleteUser(123));
        verify(userRepo).deleteById((Integer) any());
    }
}

