<#include "base/base.ftl">


<#macro title>
    Authentication
</#macro>

<#macro navbar>
</#macro>
<#macro left_sidebar>
</#macro>

<#macro body>
    <div class="container">
        <div class="row mt-5 pt-5">

            <!-- CONTENT -->
            <div class="col"></div>

            <div class="card text-center " style="width: 20rem;">
                <div class="card-header h4">
                    Authentication
                </div>

                <div class="card-body">

                    <form action="/auth" method="POST">
                        <div class="form-group">
                            <label for="username">Username</label>

                            <input type="text" name="username" class="form-control" id="username" placeholder="vasya1234"
                                   required>
                        </div>
                        <div class="form-group">
                            <label for="password">Password</label>

                            <input type="password" name="password" class="form-control" id="password" placeholder="qwerty"
                                   required>
                        </div>

                        <div class="form-group">
                            <div class="form-check">
                                <input class="form-check-input" name="remember-me" type="checkbox" id="remember-me">
                                <label class="form-check-label" for="remember-me">
                                    Remember me
                                </label>
                            </div>
                        </div>

                        <#--<#if test="${param.error!= null}">
                            <div>
                                <div class="alert alert-danger alert-dismissible">
                                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                                    Invalid credentials
                                </div>
                            </div>
                        </if>-->

                        <input type="submit" class="btn btn-primary col" value="Log in">Doesn't have an account?<br>
                        <a href="/registration" class="card-link">Registration</a>

                    </form>
                </div>
            </div>

            <div class="col"></div>
            <!-- /CONTENT -->
        </div>
    </div>
</#macro>


<@page/>