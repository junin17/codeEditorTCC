import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import './css/bootstrap.min.css';
import './css/simple-sidebar.css';
import Sidebar from 'react-sidebar';



import Editor from './view/editor/Editor'

class App extends Component {
  onChange = (newValue) => {
    console.log('change', newValue);

  }


  render() {
    return (
      

      <div className="App">
        
        <div id="wrapper" className="toggled">



          <div id="page-content-wrapper">
            <div className="container-fluid">
              <h1>Desafio Nº1</h1>
              <p>Dado um numero X, poste o fatorial do mesmo: </p>
              <select id="comboLanguages" style={{ marginBottom: '5px' }}>
                <option value="csharp">C#</option>
                <option value="java">Java</option>
                <option value="python">Python</option>
              </select>
              <div id="editor">
                <Editor />
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default App;
