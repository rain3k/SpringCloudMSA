import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import axios from 'axios';
import classNames from 'classnames';
import { Link } from 'react-router-dom';
import { Button, Badge, Card, CardBody, CardHeader, Col, Pagination, PaginationItem, PaginationLink, Row, Table } from 'reactstrap';
import { rgbToHex } from '@coreui/coreui/dist/js/coreui-utilities'


class OAuthMngList extends Component {
  constructor(props) {
    super(props);

    this.state = { 
      data : []
    };
  }

  componentDidMount() {
      this.renderData();
  }

  renderData(){
    axios.post('http://localhost:8082/service/api/oauthClientDetailss/list')
    .then( response => { 
        console.log(response.data); 
        this.setState({ data : response.data})
      } ) // SUCCESS
      .catch( response => {
      }
    );
  }

  handleClick(){
    this.props.history.push("/oauth-mng-form");
  };

  render() {

    function enabledRender(enable){
      if(enable){
        return "사용중";
      }else{
        return "사용중지";
      }
    }

    const getBadge = (status) => {
      return status === 'true' ? 'success' : 'warning'
    }

    var rowRender = this.state.data.map(function(item) {
      console.log("item:"+item)
      const userLink = `/users/${item.client_id}`
      return (
        <tr key={item.client_id}>
          <th><Link to={userLink}>{item.client_id}</Link></th>
          <td>{item.resource_ids}</td>
          <td>{item.client_secret}</td>
          <td>{item.scope}</td>
          <td>{item.authorized_grant_types}</td>
          <td>{item.web_server_redirect_uri}</td>
          <td><Badge color={getBadge(item.enabled)}>{item.autoapprove}</Badge></td>
        </tr>
      );
    });

    return (
      <div className="animated fadeIn">
        <Row>
          <Col>
            <Card>
              <CardHeader>
                <i className="fa fa-align-justify"></i> OAuth 서비스 목록
              </CardHeader>
              <CardBody>
                <Table responsive hover>
                  <thead>
                  <tr>
                    <th>클라이언트 이름</th>
                    <th>Resource ID</th>
                    <th>Client Secret</th>
                    <th>데이터 타입</th>
                    <th>인증 타입</th>
                    <th>Redirect 주소</th>
                    <th>자동승인 여부</th>
                  </tr>
                  </thead>
                  <tbody>
                    {rowRender}
                  </tbody>
                </Table>
                <Button onClick={() => this.handleClick()}>등록</Button>
              </CardBody>
            </Card>
          </Col>
        </Row>
      </div>
    );
  }
}

export default OAuthMngList;
