import Stomp from 'stomp-websocket'
import SockJS from 'sockjs-client'

// initial state
const state = {
  stompClient: null,
};

// actions
const actions = {
  connect({ state, commit }) {
    const socket = new SockJS("http://localhost:8080/api/bid");
    state.stompClient = Stomp.over(socket);
    state.stompClient.connect({}, function(frame) {
      console.log("Connected: " + frame);
      state.stompClient.subscribe("/api/topic/bidPrice", function(response) {
        //response.body
        // - id : auction id
        // - highPrice : 제일 높은 가격
        console.log(JSON.parse(response.body).highPrice);
        // TODO: 받은 id에 해당하는 auction의 최고가를 highPrice로 변경한다.
      });
    });
  },

  disconnect({ state }) {
    if (state.stompClient !== null) {
      state.stompClient.disconnect();
    }
    console.log("Disconnected");
  },

  updatePrice({ state },  auctionId ) {
    state.stompClient.send(
        "/api/bidPrice",
      {},
      JSON.stringify({ id: auctionId })
    );
  },
};


export default {
  namespaced: true,
  state,
  actions
};
