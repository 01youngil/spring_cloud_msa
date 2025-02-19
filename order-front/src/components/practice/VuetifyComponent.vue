<template>
    <v-container>
        <v-row>
            <!-- 전체 cols는 12 -->
            <v-col cols="8">
                helloworld1
            </v-col>
            <v-col cols="4">
                helloworld2
            </v-col>
        </v-row>
        <v-row>
            <v-card>
                <v-card-title>
                    회원가입
                </v-card-title>
                <v-card-text>
                    회원가입을 해주세요
                </v-card-text>
            </v-card>
        </v-row>
        <v-row>
            <v-simple-table>
                <thead>
                    <tr>
                        <th>이름</th><th>이메일</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>최영일</td><td>il@naver.com</td>
                    </tr>
                    <tr>
                        <td>최영재</td><td>jae@naver.com</td>
                    </tr>
                </tbody>
            </v-simple-table>
        </v-row>
    </v-container>

    <v-card>
        <v-card-title>
            회원가입
        </v-card-title>
        <v-card-text>
            <v-form>
                <v-text-field
                    label="email"
                    type="email"
                />
                <v-text-field
                    label="name"
                    type="text"
                />
                <v-text-field
                    label="password"
                    type="password"
                />
                <v-btn color="primary">제출하기</v-btn>
            </v-form>
        </v-card-text>
    </v-card>
    <br><br>
    <v-btn @click="cancel()">취소버튼</v-btn>
    <!-- 모달창을 위한 v-dialog -->
    <v-dialog v-model="trueOrFalse" max-width="400px">
        <v-card>
            <v-card-title>
                정말 취소하시겠습니까?
            </v-card-title>
            <v-card-actions>
                <v-btn color="primary" @click="resetModal()">예</v-btn>
                <v-btn color="secondary" @click="resetModal()">아니오</v-btn>
            </v-card-actions>
        </v-card>
    </v-dialog>
    <br><br>
    <v-btn @click="hrefRouting()">일반라우팅</v-btn>
    <v-btn @click="spaRouting1()">SPA 라우팅(router기능활용)</v-btn>
    <v-btn @click="spaRouting2()">SPA 라우팅(router기능활용-name호출)</v-btn>

</template>
<script>
    export default{
        data(){
            return{
                trueOrFalse:false,
            }
        },
        methods: {
            cancel(){
                this.trueOrFalse = true;
            },
            hrefRouting(){
                // 새로고침 발생
                window.location.href = "/practice/modeling"
            },
            spaRouting1(){
                // 새로고침 없음(spa : single page application) 보편적으로 많이씀
                this.$router.push("/practice/modeling")
            },
            spaRouting2(){
                // 새로고침 없음. 이름기반 라우팅팅
                this.$router.push({name: "ModelingComponent"})
            },
            resetModal(){
                this.trueOrFalse=false
            }
        },
    }
</script>