# MockMate WebSocket Service (`mockmate-ws`)

The `mockmate-ws` service is responsible for handling real-time communication between users in the MockMate platform. It manages peer connections, room management, and event-driven WebSocket interactions.

## **Overview**
MockMate is a peer-to-peer mock interview platform that enables users to book slots, match with peers, and conduct interviews. The `mockmate-ws` service specifically handles WebSocket-based communication after users are matched in a room.

## **Features**
- **Real-time Peer Communication**
  - Users get instant notifications when a match is found.
  - Supports WebSocket connections for seamless real-time updates.

- **Room Management**
  - Users join rooms when they are matched with a peer.
  - Room-based events ensure efficient message delivery.

- **Interview Session Management**
  - Manages the interview lifecycle (waiting, in-progress, completed).
  - Notifies users when an interview starts or ends.

## **Architecture**
The `mockmate-ws` service is independent of the main API (`mockmate-api`) but interacts with it for user session validation and event updates.

```
         ┌──────────────────────────────┐
         │  MockMate API (Spring Boot)  │
         └──────────────────────────────┘
                     │
                     ▼
        ┌───────────────────────────┐
        │ MockMate WebSocket Service │
        └───────────────────────────┘
                     │
                     ▼
           ┌─────────────────┐
           │ Connected Users │
           └─────────────────┘
```

## **Tech Stack**
- **Backend:** Spring Boot (Java)
- **WebSockets:** STOMP Protocol over WebSockets
- **Deployment:** Docker & AWS
- **Database:** PostgreSQL (for session tracking)

## **Setup Instructions**
### **1. Clone the Repository**
```sh
git clone https://github.com/your-repo/mockmate-ws.git
cd mockmate-ws
```

### **2. Set Up Environment Variables**
Copy `.env.example` to `.env` and update WebSocket and database configurations.
```sh
cp .env.example .env
```

### **3. Start the WebSocket Service**
```sh
docker-compose up --build room-ws-service
```

## **WebSocket Endpoints**
### **Connect to WebSocket**
- WebSocket URL: `ws://localhost:9090/ws`
- Connect using STOMP over WebSockets.

### **Event Messages**
| Event          | Description                                    |
|---------------|--------------------------------|
| `JOIN_ROOM`   | User joins a waiting room.  |
| `MATCHED`     | Notification sent when a peer is matched.  |
| `SESSION_STARTED` | Sent when an interview session begins. |
| `SESSION_ENDED` | Sent when an interview session ends. |

### **Example WebSocket Message**
```json
{
  "event": "JOIN_ROOM",
  "userId": "1234",
  "roomId": "5678"
}
```

## **How It Works**
1. **User joins a waiting room** (`JOIN_ROOM` event).
2. **When a match is found**, the service sends a `MATCHED` event to notify both users.
3. **When the interview starts**, a `SESSION_STARTED` event is sent.
4. **When the interview ends**, a `SESSION_ENDED` event is sent.

## **Contributing**
PRs are welcome for improving real-time interactions.

## **License**
MIT License
```