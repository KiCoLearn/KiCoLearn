<template>
    <div class="container">
        <div class="list-board">
            <div
                id="app"
                class="paper"
            >
                <div class="list">
                    <div id="example">
                        <carousel
                            class="slide-container"
                            :autoplay="false"
                            :autoplay-hover-pause="true"
                            :speed="300"
                        >
                            <slide
                                v-for="(quest, i) in quests"
                                :key="i"
                                class="custom-slide"
                            >
                                <div
                                    class="tile pointer mx-auto my-auto"
                                    @click="postDetail(post.id)"
                                >
                                    <div class="tile-media">
                                        <div class="content">
                                            <div class="content-overlay" />
                                            <h5>이미지</h5>
                                            <div class="content-details fadeIn-top" />
                                        </div>
                                    </div>
                                </div> 
                            </slide>
                        </carousel>
                    </div>
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

<script lang="js">
import {Carousel, Slide} from 'vue-carousel';
export default {
    components:{
        Carousel,
        Slide,
    },
    data() {
        return {
            quests: [
                {
                    id: 1,
                    title: '미션 성공',
                    price: 500,
                    imgsrc: require('@/assets/questsuc.png')
                },
                {
                    id: 2,
                    title: '깁밥',
                    price: 749,
                    imgsrc: require('@/assets/questsuc.png')
                },
                {
                    id: 3,
                    title: '용돈',
                    price: 949,
                    imgsrc: require('@/assets/questsuc.png')
                },
            ],
           
        };
    },
        
};
</script>
<style scoped>
@import url(https://fonts.googleapis.com/css?family=Gochi+Hand);
@import url(//fonts.googleapis.com/earlyaccess/notosanstc.css);
.container {
  position: relative;
  width: 100%;
  height: 40rem;
  top: 23rem;
  left: 50%;
  transform: translate(-50%, -50%);
}
.container .list-board {
  position: absolute;
  width: 80%;
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
  background: #e5e3d1;
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

.toggle {
  cursor: pointer;
  position: absolute;
  width: 10px;
  height: 10px;
  border-radius: 3px;
  left: 10px;
  border: 2.5px solid #dfa35a;
  transition: .3s;
}
.toggle i {
  position: absolute;
  font-size: 1em;
  color: #dfa35a;
  top: -4px;
}

.todo-text {
  color: #50140d;
  font-size: 1.2em;
  transition: .3s;
}

.todo-item, .done-item {
  transition: .5s;
  display: flex;
  align-items: center;
  height: 22px;
}
.todo-item:hover .delete-icon, .done-item:hover .delete-icon {
  opacity: 1;
}

.delete-icon {
  position: absolute;
  right: 10px;
  opacity: 0;
  transition: .3s;
}
.delete-icon i {
  position: absolute;
  font-size: 1.3em;
  color: #d28070;
  right: 5px;
  top: -7px;
}

</style>