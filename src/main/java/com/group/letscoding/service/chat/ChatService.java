//package com.group.letscoding.service.chat;
//
//import com.group.letscoding.domain.chat.ChatRoom;
//import com.group.letscoding.domain.chat.ChatRoomRepository;
//import com.group.letscoding.domain.chat.MessageRepository;
//import com.group.letscoding.domain.user.User;
//import com.group.letscoding.domain.user.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class ChatService {
//    private final UserRepository userRepository;
//    private final ChatRoomRepository chatRoomRepository;
//    private final MessageRepository messageRepository;
//
//    @Autowired
//    public ChatService(UserRepository userRepository, ChatRoomRepository chatRoomRepository, MessageRepository messageRepository) {
//        this.userRepository = userRepository;
//        this.chatRoomRepository = chatRoomRepository;
//        this.messageRepository = messageRepository;
//    }
//
//    public ChatRoom createChatRoom(ChatRoom chatRoom) {
////        // 채팅 방 생성 및 저장 로직을 구현합니다.
////        // chatRoom 객체를 사용하여 데이터베이스에 저장하고 생성된 ChatRoom을 반환합니다.
////        // 예를 들어, chatRoomRepository.save(chatRoom)와 같은 방식으로 저장할 수 있습니다.
////
////        chatRoom = chatRoomRepository.findById(chatRoomId)
////                .orElseThrow(() -> new RuntimeException("Chat room not found"));
////
////        if(!chatRoom.getUsers().contains(user)) {
////            chatRoom.getUsers().add(user);
////            // 필요하면 User Entity에 대한 처리도 해야 합니다.
////            // user.getChatRooms().add(chatRoom);
////        }
//
//        return chatRoomRepository.save(chatRoom);
//    }
//
//    public ChatRoom joinChatRoom(Long chatRoomId, User user) {
//        // 기존 채팅 방에 사용자를 추가하는 로직을 구현합니다.
//        // chatRoomId와 user 정보를 사용하여 채팅 방을 찾고 사용자를 추가한 뒤 반환합니다.
//        // 예를 들어, ChatRoom 객체에서 users 컬렉션에 사용자를 추가한 후, 저장하면 됩니다.
//        ChatRoom chatRoom = chatRoomRepository.findById
//                (chatRoomId).orElseThrow(() -> new RuntimeException("Chat room not found"));
//        chatRoom.getUsers().add(user);
//        return chatRoomRepository.save(chatRoom);
//    }
//
//
//    // 사용자 관련 서비스 메서드
//    // 채팅 방 관련 서비스 메서드
//    // 메시지 관련 서비스 메서드
//}
