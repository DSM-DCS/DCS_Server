package com.dsm.dcs.service.user;

import com.dsm.dcs.dto.response.DeliveryListResponse;
import com.dsm.dcs.dto.response.UserListResponse;
import com.dsm.dcs.entity.delivery.Delivery;
import com.dsm.dcs.entity.user.User;
import com.dsm.dcs.facade.DeliveryFacade;
import com.dsm.dcs.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserFacade userFacade;
    private final DeliveryFacade deliveryFacade;

    public UserListResponse getUser() {
        List<User> userList = userFacade.getUserList();
        return getUserList(userList);
    }

    public UserListResponse searchUser(String name) {
        List<User> userList = userFacade.getUserByName(name);
        return getUserList(userList);
    }

    public DeliveryListResponse getDeliveryList(Long userId) {
        List<DeliveryListResponse.DeliveryResponse> deliveryResponses = new ArrayList<>();
        List<Delivery> deliveries = deliveryFacade.getDeliveryList(userFacade.getCurrentUser());

        for(Delivery delivery : deliveries) {
            deliveryResponses.add(
                    DeliveryListResponse.DeliveryResponse.builder()
                            .courierCompany(delivery.getCourierCompany().name())
                            .name(delivery.getUser().getName())
                            .createdDate(delivery.getCreatedDate())
                            .build()
            );
        }

        return new DeliveryListResponse(deliveryResponses);
    }

    private UserListResponse getUserList(List<User> userList) {

        List<UserListResponse.UserResponse> userResponses = new ArrayList<>();

        for(User user : userList) {
            userResponses.add(
                    UserListResponse.UserResponse.builder()
                            .name(user.getName())
                            .studentNumber(user.getStudentNumber())
                            .build()
            );
        }

        return new UserListResponse(userResponses);
    }


}
