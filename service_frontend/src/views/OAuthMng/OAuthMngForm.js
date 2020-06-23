import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import axios from 'axios';
import classNames from 'classnames';
import { Button, Card, CardBody, CardFooter, Col, Container, Form, Input, InputGroup, InputGroupAddon, InputGroupText, Row } from 'reactstrap';
import { rgbToHex } from '@coreui/coreui/dist/js/coreui-utilities'


class OAuthMngForm extends Component {
  constructor(props) {
    super(props);

    this.state = { };
  }

  onChange = (e) => {
    this.setState({ [e.target.name]: e.target.value });
  }

  componentDidMount() {

  }

  onSubmit = (e) => {
    e.preventDefault();

    axios.post('http://127.0.0.1:8082/service/api/oauthClientDetailss/insert',this.state)
    .then( response => { 
        console.log(response.data); 
        this.props.history.push("/oauth-mng-list");
      } ) // SUCCESS
      .catch( response => {
      }
    );
  }

  handleClick(){ 
    this.props.history.push("/oauth-mng-list");
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
                <CardBody className="p-4"> 
                  <Form onSubmit={this.onSubmit}>
                    <h1>OAuth 서비스 정보</h1>
                    <p className="text-muted">저장할 OAuth정보를 입력해주세요.</p>
                    <InputGroup className="mb-3">
                      <InputGroupAddon addonType="prepend">
                        <InputGroupText>
                          <i className="icon-user"></i>
                        </InputGroupText>
                      </InputGroupAddon>
                      <Input name="client_id" type="text" placeholder="클라이언트 이름" onChange={this.onChange} required/>
                    </InputGroup>
                    <InputGroup className="mb-3">
                      <InputGroupAddon addonType="prepend">
                        <InputGroupText>
                          <i className="icon-lock"></i>
                        </InputGroupText>
                      </InputGroupAddon>
                      <Input name="resource_ids" type="text" placeholder="Resource ID" onChange={this.onChange} required/>
                    </InputGroup>
                    <InputGroup className="mb-3">
                      <InputGroupAddon addonType="prepend">
                        <InputGroupText>
                          <i className="icon-lock"></i>
                        </InputGroupText>
                      </InputGroupAddon>
                      <Input name="client_secret" type="text" placeholder="Client Secret" onChange={this.onChange} required/>
                    </InputGroup>
                    <InputGroup className="mb-3">
                      <InputGroupAddon addonType="prepend">
                        <InputGroupText>
                          <i className="icon-lock"></i>
                        </InputGroupText>
                      </InputGroupAddon>
                      <Input name="scope" type="text" placeholder="데이터 타입" onChange={this.onChange} required/>
                    </InputGroup>
                    <InputGroup className="mb-3">
                      <InputGroupAddon addonType="prepend">
                        <InputGroupText>
                          <i className="icon-lock"></i>
                        </InputGroupText>
                      </InputGroupAddon>
                      <Input name="authorized_grant_types" type="text" placeholder="인증 타입" onChange={this.onChange} required/>
                    </InputGroup>
                    <InputGroup className="mb-3">
                      <InputGroupAddon addonType="prepend">
                        <InputGroupText>
                          <i className="icon-lock"></i>
                        </InputGroupText>
                      </InputGroupAddon>
                      <Input name="web_server_redirect_uri" type="text" placeholder="Redirect 주소" onChange={this.onChange} required/>
                    </InputGroup>
                    <InputGroup className="mb-3">
                      <InputGroupAddon addonType="prepend">
                        <InputGroupText>
                          <i className="icon-shield"></i>
                        </InputGroupText>
                      </InputGroupAddon>
                      <Input type="select" name="autoapprove" placeholder="자동승인 여부" onChange={this.onChange}  value="ADMIN">
                        <option value="true">예</option>
                        <option value="false">아니오</option>
                      </Input>
                    </InputGroup>
                    <Button type="submit">저장</Button>
                    <Button onClick={() => this.handleClick()}>목록</Button>
                  </Form>
                </CardBody>
              </Card>
            </Col>
        </Row>
      </div>
    );
  }
}

export default OAuthMngForm;
