import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import axios from 'axios';
import classNames from 'classnames';
import { Button, Card, CardBody,CardHeader, CardFooter, Col, Container, Form, Input, InputGroup, InputGroupAddon, InputGroupText, Row } from 'reactstrap';
import { rgbToHex } from '@coreui/coreui/dist/js/coreui-utilities'


class AccountMngForm extends Component {
  constructor(props) {
    super(props);

    this.state = { 
      username : '',
      password : '',
      authority : 'ADMIN'
    };
  }

  onChange = (e) => {
    this.setState({ [e.target.name]: e.target.value });
  }

  componentDidMount() {

  }

  onSubmit = (e) => {
    e.preventDefault();

    const data = {
      username: this.state.username,
      password: this.state.password,
      authority: this.state.authority
    };

    axios.post('http://127.0.0.1:8082/service/api/users/insert',data)
    .then( response => { 
        console.log(response.data); 
        this.props.history.push("/account-mng-list");
      } ) // SUCCESS
      .catch( response => {
      }
    );
  }

  handleClick(){
    this.props.history.push("/account-mng");
  };

  render() {

    function enabledRender(enable){
      if(enable){
        return "사용중";
      }else{
        return "사용중지";
      }
    }

    return (
      <div className="animated fadeIn">
        <Row>
          <Col>
              <Card className="mx-4">
                <CardHeader>
                  <i className="fa fa-align-justify"></i> 계정정보
                </CardHeader>
                <CardBody className="p-4"> 
                  <Form onSubmit={this.onSubmit}>
                    <p className="text-muted">저장할 계정정보를 입력해주세요.</p>
                    <InputGroup className="mb-3">
                      <InputGroupAddon addonType="prepend">
                        <InputGroupText>
                          <i className="icon-user"></i>
                        </InputGroupText>
                      </InputGroupAddon>
                      <Input name="username" type="text" placeholder="Username" autoComplete="username" onChange={this.onChange} required/>
                    </InputGroup>
                    <InputGroup className="mb-3">
                      <InputGroupAddon addonType="prepend">
                        <InputGroupText>
                          <i className="icon-lock"></i>
                        </InputGroupText>
                      </InputGroupAddon>
                      <Input name="password" type="password" placeholder="비밀번호" autoComplete="new-password" onChange={this.onChange} required/>
                    </InputGroup>
                    <InputGroup className="mb-3">
                      <InputGroupAddon addonType="prepend">
                        <InputGroupText>
                          <i className="icon-shield"></i>
                        </InputGroupText>
                      </InputGroupAddon>
                      <Input type="select" name="authority" placeholder="권한" onChange={this.onChange}  value="ADMIN">
                        <option value="ADMIN">관리자</option>
                        <option value="USER">사용자</option>
                      </Input>
                    </InputGroup>
                  </Form>
                </CardBody>
                <CardFooter>
                  <Button type="submit">저장</Button>
                  <Button onClick={() => this.handleClick()}>목록</Button>
                </CardFooter>
              </Card>
            </Col>
        </Row>
      </div>
    );
  }
}

export default AccountMngForm;
