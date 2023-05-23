<template>
    <div>
        <b-card-group deck>
          <b-card
            title="Title"
            img-src="https://picsum.photos/300/300/?image=41"
            img-alt="Image"
            img-top
            @click="this.showPlanView"
            style="cursor: pointer"
          >
            <b-card-text>
              This is a wider card with supporting text below as a natural
              lead-in to additional content. This content is a little bit
              longer.
            </b-card-text>
            <template #footer>
              <small class="text-muted">Last updated 3 mins ago</small>
            </template>
          </b-card>
          <b-card
            title="Title"
            img-src="https://picsum.photos/300/300/?image=41"
            img-alt="Image"
            img-top
            @click="this.showPlanView"
            style="cursor: pointer"
          >
            <b-card-text>
              This is a wider card with supporting text below as a natural
              lead-in to additional content. This content is a little bit
              longer.
            </b-card-text>
            <template #footer>
              <small class="text-muted">Last updated 3 mins ago</small>
            </template>
          </b-card>
        </b-card-group>
    </div>

</template>

<script>
export default {
    methods: {
        showPlanView() {
            console.log("글 상세보기 띄워라");
        }
    }
    

}
</script>

<style>

</style>