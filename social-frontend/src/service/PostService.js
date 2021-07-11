export default class PostService {

    async getAll() {
        return await fetch("http://localhost:8080/posts")
    }
}

