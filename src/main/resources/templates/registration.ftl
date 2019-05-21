<#include "base/base.ftl">


<#macro title>
    Registration
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
                    Registration
                </div>

                <div class="card-body">

                    <form method="POST">
                        <div class="form-group">
                            <label for="username">Username <!--Имя пользователя--></label>
                            <input type="text" name="username" class="form-control" id="username"
                                   onchange="validUsername('username');"
                                   placeholder="vasya1234" required>
                        </div>
                        <div class="form-group">
                            <label for="password">Password</label>

                            <input type="password" name="password" class="form-control" id="password"
                                   onchange="validatePassword();"
                                   placeholder="qwerty" required>
                            <label for="confirm-password">Repeat password</label>
                            <input type="password" class="form-control" id="confirm-password"
                                   onkeyup="validatePassword();"
                                   placeholder="qwerty" required>
                        </div>

                        <div class="form-group">
                            <input type="submit" class="btn btn-primary col" value="Registration">
                        </div>
                        Already have an account?<br>
                        <a href="/auth" class="card-link">Log in</a>
                    </form>
                </div>
            </div>

            <div class="col"></div>
            <!-- /CONTENT -->
        </div>
    </div>
</#macro>


<@page/>