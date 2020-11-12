<template>
    <div class="container">
        <div class="list-board">
            <div
                id="app"
                class="paper"
            >
                <div class="quest-list">
                    <ul
                        v-for="quest in quests"
                        :key="quest.kidId"
                    >
                        <v-card class="questlist">
                            <v-layout column>
                                <v-layout column>
                                    <v-card-title><h5>{{ quest.name }}</h5></v-card-title>
                                    <v-card-text>{{ quest.reward }}</v-card-text>
                                    <v-card-text>{{ quest.description }}</v-card-text>
                                </v-layout>
                            </v-layout>
                        </v-card>
                        <br>
                    </ul>
                </div>
                <div class="buttons">
                    <div class="delete" />
                </div>
            </div>
            <div class="clip">
                <div class="clip-gray" />
                <div class="gray-line" />
            </div>
            <div class="note" />
        </div>
    </div>
</template>

<script>
import axios from '@/plugins/axios';
export default {
    name : 'KidQuest',
    data() {
        return {
            kidId:1,
            quests: new Array(),
        };
    },
    created() {
        axios.get(process.env.VUE_APP_API_URL + '/api/quest/kid/list/'+this.kidId)
            .then((res) => {
                console.log(res);
                console.log(res.data.data);
                this.quests = res.data.data;
            })
            .catch(err => {
                console.log(err);
            });

    },
        
};
</script>
<style scoped>
@import url(https://fonts.googleapis.com/css?family=Gochi+Hand);
@import url(//fonts.googleapis.com/earlyaccess/notosanstc.css);


#app {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    height: 90%;
}
.container {
  position: relative;
  width: 100%;
  height: 600px;
  top: 23rem;
  left: 50%;
  transform: translate(-50%, -50%);
}
.container .list-board {
  position: absolute;
  width: 100%;
  height: 100%;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  border-radius: 15px;
  background: #d28070;
  box-shadow: 3px 3px 0 rgba(80, 20, 13, 0.3);
}
.container .list-board .paper {
  font-family: 'Gochi Hand', 'Noto Sans TC';
  position: absolute;
  background: #ffe8d6;
  width: 86%;
  height: 90%;
  left: 7%;
  top: 5%;
  box-shadow: 2px 2px 0 rgba(80, 20, 13, 0.3);
}
.container .list-board .paper .input-box {
  position: absolute;
  width: 66%;
  height: 5%;
  left: 16%;
  top: 7%;
  border-color: transparent;
  background: #f3f1e7;
  color: #50140d;
  font-family: 'Gochi Hand', 'Noto Sans TC';
}
.container .list-board .paper .input-box:focus {
  outline: none;
}
.container .list-board .paper .list {
  position: absolute;
  width: 80%;
  height: 70%;
  left: 10%;
  top: 18%;
  transition: .3s;
}
.container .list-board .paper .list .list-ul {
  width: 100%;
  display: block;
  list-style: none;
}
.container .clip {
  position: absolute;
  width: 35%;
  height: 6.7%;
  border-radius: 1rem;
  background: #dcdcdd;
  left: 33%;
  top: -1.5%;
}
.container .clip:before {
  position: absolute;
  content: "";
  width: 70%;
  height: 100%;
  border-radius: 6px;
  background: #dcdcdd;
  top: -70%;
  left: 15%;
  z-index: -1;
}
.container .clip:after {
  position: absolute;
  content: "";
  width: 0;
  height: 0;
  border: 14px solid black;
  border-color: #50140d transparent transparent #50140d;
  right: -28px;
  bottom: 0px;
  opacity: 0.3;
}
.container .clip .clip-gray {
  position: absolute;
  width: 100%;
  height: 30%;
  background: #b4b5b5;
  bottom: 0;
  box-shadow: 0 1.5px 0.1px rgba(0, 0, 0, 0.35);
}
.container .clip .gray-line {
  position: absolute;
  width: 50%;
  height: 7%;
  left: 25%;
  top: -50%;
  border-radius: 4px;
  background: #b4b5b5;
  z-index: 2;
}
.container .clip .gray-line:before {
  position: absolute;
  content: "";
  width: 100%;
  height: 100%;
  border-radius: 4px;
  background: #b4b5b5;
  top: 250%;
}
.container .clip .gray-line:after {
  position: absolute;
  content: "";
  width: 100%;
  height: 100%;
  border-radius: 4px;
  background: #b4b5b5;
  top: 500%;
}
.container .note {
  position: absolute;
  width: 25%;
  height: 5%;
  top: 9%;
  left: -8%;
  background: rgba(223, 163, 90, 0.5);
  transform: rotate(-8deg);
}
.container .note:before {
  position: absolute;
  content: "";
  width: 25%;
  height: 100%;
  background: rgba(223, 163, 90, 0.4);
  right: 0;
  box-shadow: 0.5px 0.5px rgba(80, 20, 13, 0.2);
}
.container .note:after {
  position: absolute;
  content: "";
  top: 20%;
  left: 4%;
}
.quest-list{
  padding-top: 10px;
  text-align: center;
  width: 90%;
}

</style>