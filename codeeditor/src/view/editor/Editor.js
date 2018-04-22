import React, { Component } from 'react';
import AceEditor from 'react-ace';

import 'brace/mode/java';
import 'brace/theme/monokai';
import 'brace/theme/terminal';


export default class Editor extends Component {
    constructor() {
        super();

        this.modo = "java";
        this.tema = "monokai"
    }

    render() {
        return (
            <AceEditor
                mode={this.modo}
                theme={this.tema}
                name="editor"
                width="700px"
                editorProps={{ $blockScrolling: true }}
            />
        );
    }
}