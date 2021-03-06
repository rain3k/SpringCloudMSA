import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import { Alert, Button, Card, CardBody, CardGroup, Col, Container, Form, Input, InputGroup, InputGroupAddon, InputGroupText, Row } from 'reactstrap';
import axios from 'axios';
import Alerts from '../Notifications/Alerts/Alerts.js';

class OAuthTestLogin extends Component {
  constructor(props) {
    super(props);
    this.state = {
      inputUsername: '',
      inputPassword: '',
      errorMessageAlertShow:false,
      errorMessage:''
    };
  }

  handleUsernameChange(e){
    this.setState({ inputUsername: e.target.value });
  };

  handlePasswordChange(e){
    this.setState({ inputPassword: e.target.value });
  };

  handleClick(){
    const data = {
      'username': this.state.inputUsername,
      'password': this.state.inputPassword
    };

    const config = {
      header: {
        'X-Requested-With': 'XMLHttpRequest'
      }
    };

    axios.post('http://127.0.0.1:8082/service/api/auth/login', data, config)
    .then( response => { 
        localStorage.setItem('token',response.data.token);
        localStorage.setItem('username',this.state.username);
        console.log(response); 
        this.setState({ errorMessageAlertShow: true });
        this.setState({ errorMessage: response.data.token });
      } ) // SUCCESS
      .catch( response => {
        this.setState({ errorMessageAlertShow: true });
        this.setState({ errorMessage: response.message });
        console.log(response.message); 
      }
    ); // ERROR
  };
  render() {
    return (
      <div className="app flex-row align-items-center">
        <Container>
          <Row className="justify-content-center">
            <Col md="8">
              <CardGroup>
                <Card className="p-4">
                  <CardBody>
                    <Form>
                      <h1>Login</h1>
                      <p className="text-muted">Sign In to your account</p>
                      <InputGroup className="mb-3">
                        <InputGroupAddon addonType="prepend">
                          <InputGroupText>
                            <i className="icon-user"></i>
                          </InputGroupText>
                        </InputGroupAddon>
                        <Input type="text" placeholder="Username" autoComplete="username" onChange={evt => this.handleUsernameChange(evt)}/>
                      </InputGroup>
                      <InputGroup className="mb-4">
                        <InputGroupAddon addonType="prepend">
                          <InputGroupText>
                            <i className="icon-lock"></i>
                          </InputGroupText>
                        </InputGroupAddon>
                        <Input type="password" placeholder="Password" autoComplete="current-password" onChange={evt => this.handlePasswordChange(evt)}/>
                      </InputGroup>
                      <Row>
                        <Col xs="6">
                          <Button color="primary" className="px-4" onClick={() => this.handleClick()}>Login</Button>
                        </Col>
                        <Col xs="6" className="text-right">
                          <Button color="link" className="px-0">Forgot password?</Button>
                        </Col>
                      </Row>
                    </Form>
                  </CardBody>
                </Card>
                <Card className="text-white bg-primary py-5 d-md-down-none" style={{ width: '44%' }}>
                  <CardBody className="text-center">
                    <div>
                      <h2>Sign up</h2>
                      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut
                        labore et dolore magna aliqua.</p>
                      <Link to="/register">
                        <Button color="primary" className="mt-3" active tabIndex={-1}>Register Now!</Button>
                      </Link>
                    </div>
                  </CardBody>
                </Card>
              </CardGroup>
              <Alert color="danger" isOpen={this.state.errorMessageAlertShow}><p>{this.state.errorMessage}</p></Alert>
            </Col>
          </Row>
        </Container>
      </div>
    );
  }
}

export default OAuthTestLogin;
