<%@page contentType="text/html" pageEncoding="UTF-8" %>
<div class="main-container">
    <div class="debts-container" flex="30">
        <md-toolbar class="friends-toolbar">
            <div class="md-toolbar-tools">
                <h2 class="md-flex">Друзья</h2>
            </div>
        </md-toolbar>
        <md-content flex layout-padding>
            <div layout="row">
                <md-input-container class="md-block" flex style="margin: 0">
                    <label>Поиск</label>
                    <input ng-model="searchUserText" press-enter="searchUsers(searchUserText)">
                </md-input-container>
                <md-button ng-click="searchUsers(searchUserText)"
                           class="md-icon-button"
                           title="Найти">
                    <md-icon md-svg-icon="/resources/img/icons/svg/search_black.svg"></md-icon>
                </md-button>
                <md-button ng-click="clearSearch()"
                           class="md-icon-button"
                           title="Отменить поиск">
                    <md-icon md-svg-icon="/resources/img/icons/svg/clear_black.svg"></md-icon>
                </md-button>
            </div>
            <div>
                <md-card ng-repeat="friend in searchUsersAmongFriends(searchUserText) track by friend.id">
                    <md-card-title>
                        <md-card-title-text>
                            <span class="md-title">
                                <md-icon md-svg-icon="/resources/img/icons/svg/face_black.svg"></md-icon>
                                 {{friend.firstName}} {{friend.lastName}}
                            </span>
                        </md-card-title-text>
                        <span></span>
                        <md-button ng-click="removeFriend(friend, $index)"
                                   class="md-icon-button"
                                   title="Убрать из друзей">
                            <md-icon md-svg-icon="/resources/img/icons/svg/remove_circle_outline_black.svg"></md-icon>
                        </md-button>
                    </md-card-title>
                    <md-card-content>
                        <p>{{friend.login}}</p>
                    </md-card-content>
                </md-card>
            </div>
            <div ng-if="searching">
                <md-card ng-repeat="user in foundedUsers track by user.id">
                    <md-card-title>
                        <md-card-title-text>
                            <span class="md-title">
                                <md-icon md-svg-icon="/resources/img/icons/svg/face_black.svg"></md-icon>
                                 {{user.firstName}} {{user.lastName}}
                            </span>
                        </md-card-title-text>
                        <span></span>
                        <md-button ng-click="addFriend(user)"
                                   class="md-icon-button"
                                   title="Добавить в друзья">
                            <md-icon md-svg-icon="/resources/img/icons/svg/add_circle_outline_black.svg"></md-icon>
                        </md-button>
                    </md-card-title>
                    <md-card-content>
                        <p>{{user.login}}</p>
                    </md-card-content>
                </md-card>
            </div>
        </md-content>
    </div>
    <div class="debts-container" flex="30">
        <md-toolbar class="md-warn">
            <div class="md-toolbar-tools">
                <h2 class="md-flex">Мои долги (туда)</h2>
                <span flex></span>
                <md-button class="md-icon-button"
                           title="Создать долг"
                           ng-click="createDebt()">
                    <md-icon md-svg-icon="/resources/img/icons/svg/add_circle_outline_white.svg"></md-icon>
                </md-button>
            </div>
        </md-toolbar>
        <md-content flex layout-padding>
            <div>
                <md-input-container class="md-block" flex style="margin: 0">
                    <label>Поиск</label>
                    <input ng-model="searchOutgoingDebtsText">
                </md-input-container>
            </div>
            <md-card ng-repeat="debt in searchDebts(searchOutgoingDebtsText, outgoingDebts, true) track by debt.id">
                <md-card-title>
                    <md-card-title-text>
                        <span class="md-headline">{{debt.whom.firstName}} {{debt.whom.lastName}}</span>
                        <span class="md-caption">{{debt.initDateTime | date : 'd/M/yy H:mm'}}</span>
                    </md-card-title-text>
                </md-card-title>
                <md-card-content>
                    <span class="md-title">{{debt.money}} &#8381;</span>
                    <p>{{debt.description}}</p>
                </md-card-content>
            </md-card>
        </md-content>
    </div>
    <div class="debts-container" flex="30">
        <md-toolbar>
            <div class="md-toolbar-tools">
                <h2 class="md-flex">Мои долги (сюда)</h2>
            </div>
        </md-toolbar>
        <md-content flex layout-padding>
            <div>
                <md-input-container class="md-block" flex style="margin: 0">
                    <label>Поиск</label>
                    <input ng-model="searchIncomingDebtsText">
                </md-input-container>
            </div>
            <md-card ng-repeat="debt in searchDebts(searchIncomingDebtsText, incomingDebts, false) track by debt.id">
                <md-card-title>
                    <md-card-title-text>
                        <span class="md-headline">{{debt.who.firstName}} {{debt.who.lastName}}</span>
                        <span class="md-caption">{{debt.initDateTime | date : 'd/M/yy H:mm'}}</span>
                    </md-card-title-text>
                    <span flex></span>
                    <md-button class="md-icon-button"
                               title="Удалить долг"
                               ng-click="removeDebt(debt)">
                        <md-icon md-svg-icon="/resources/img/icons/svg/remove_circle_outline_black.svg"></md-icon>
                    </md-button>
                </md-card-title>
                <md-card-content>
                    <span class="md-title">{{debt.money}} &#8381;</span>
                    <p>{{debt.description}}</p>
                </md-card-content>
            </md-card>
        </md-content>
    </div>
</div>