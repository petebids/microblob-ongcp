import PostService from "../service/PostService"
import React from "react";
import {JsonTable} from "react-json-table";

var postService = new PostService();

export class GetAllPosts extends React.Component {
    constructor(props) {
        super(props);
        this.postService = postService;

    }

    async componentDidMount() {
        this.fetchALl()

    }

    fetchALl() {
        this.postService.getAll()
            .then(response => {
                console.log(response)
                response.json()
            })
            .then(result => {
                this.setState({posts: result})
            })

    }

    render() {
        return <div>
            <JsonTable rows={this.posts}/>
        </div>

    }
}

