<template>
        <div>
            <v-breadcrumb title="마이페이지" description="지갑을 생성하거나 작품을 업로드 할 수 있습니다."></v-breadcrumb>
            <div class="container">
                <mypageNav></mypageNav>
                <div id="mywallet-info" class="row">
                    <div class="col-md-12 mt-5">
                        <div class="card">
                            <table class="table table-bordered">
                                <tr>
                                    <th>총보유 ETH</th>
                                    <td class="text-right">{{ wallet['잔액'] }} ETH</td>
                                    <td colspan="2">
                                        <button type="button" class="btn btn-secondary" v-on:click="charge()" v-bind:disabled="isCharging">{{ isCharging ? "충전중" : "ETH 충전하기"}}</button>
                                    </td>
                                </tr>
                                <tr>
                                    <th>내 지갑주소</th>
                                    <td class="text-right">{{ wallet['주소'] }}</td>
                                    <th>충전 회수</th>
                                    <td class="text-right">{{ wallet['충전회수'] }}회</td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
</template>
<script>
import breadcrumb from '../common/breadcrumb.vue'
import mypageNav from '../common/mypage-nav.vue'

export default {
    data() {
        return {
            wallet: {
                id:0,
                "소유자id":15,
                "주소": "",
                "잔액": 0,
                "충전회수": 0
            },
            isCharging: false,          // 현재 코인을 충전하고 있는 중인지 확인하는 변수
            sharedState: this.$store.state
        }
    },
    components : {
      breadcrumb,
      mypageNav
    },
    methods: {
        // ETH 충전하기
        charge: function(){
            var scope = this;
            scope.isCharging = true;

            this.$walletService.chargeEther(this.wallet['주소'], function(response){
                scope.isCharging = false;

                alert("코인이 충전 되었습니다.");
                scope.fetchWalletInfo();
            })
        },
        // 지갑 정보 가져오기
        fetchWalletInfo: function(){
            var scope = this;
            var web3 = new Web3(new Web3.providers.HttpProvider(BLOCKCHAIN_URL));

            this.$walletService.findById(this.sharedState.user.id, function(data){
                console.log(data)
                // TODO API 호출로 지갑 정보를 가져와 보여줍니다.
                scope.wallet.id = data.id
                scope.wallet.소유자id = data.소유자id
                scope.wallet.주소 = data.주소
                scope.wallet.잔액 = web3.utils.fromWei(`${data.잔액}`, "ether")
                scope.wallet.충전회수 = data.충전회수
                // web3를 사용하여 잔액을 조회해 보는 것도 포함해보도록 합니다.
                web3.eth.getBalance(scope.wallet.주소).then(console.log)
            });
        }
    },
    mounted: function(){
        this.fetchWalletInfo();
        console.log(this.$store.state)
    }
}

</script>
