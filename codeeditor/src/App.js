import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';

import Editor from './view/editor/Editor'

class App extends Component {
  onChange = (newValue) => {
    console.log('change', newValue);
  }


  render() {
    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <h1 className="App-title">Welcome to React</h1>
        </header>
        <div id="editor">
          <Editor/>
        </div>
      </div>
    );
  }
}

export default App;
