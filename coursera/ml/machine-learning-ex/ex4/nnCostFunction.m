function [J grad] = nnCostFunction(nn_params, ...
                                   input_layer_size, ...
                                   hidden_layer_size, ...
                                   num_labels, ...
                                   X, y, lambda)
%NNCOSTFUNCTION Implements the neural network cost function for a two layer
%neural network which performs classification
%   [J grad] = NNCOSTFUNCTON(nn_params, hidden_layer_size, num_labels, ...
%   X, y, lambda) computes the cost and gradient of the neural network. The
%   parameters for the neural network are "unrolled" into the vector
%   nn_params and need to be converted back into the weight matrices. 
% 
%   The returned parameter grad should be a "unrolled" vector of the
%   partial derivatives of the neural network.
Theta1 = reshape(nn_params(1:hidden_layer_size * (input_layer_size + 1)), ...
                 hidden_layer_size, (input_layer_size + 1));

Theta2 = reshape(nn_params((1 + (hidden_layer_size * (input_layer_size + 1))):end), ...
                 num_labels, (hidden_layer_size + 1));
m = size(X, 1);
J = 0;
Theta1_grad = zeros(size(Theta1));
Theta2_grad = zeros(size(Theta2));
% ====================== YOUR CODE HERE ======================
% Instructions: You should complete the code by working through the
%               following parts.
%
% Part 1: Feedforward the neural network and return the cost in the
%         variable J. After implementing Part 1, you can verify that your
%         cost function computation is correct by verifying the cost
%         computed in ex4.m
a2 = sigmoid([ones(m, 1) X] * Theta1');
a3 = sigmoid([ones(m, 1) a2] * Theta2');
%               first time.

% Back propagation
for t=1:m

    % Step 1
	a1 = X(t,:); % X already have a bias Line 44 (1*401)
    a1 = a1'; % (401*1)
	z2 = Theta1 * a1; % (25*401)*(401*1)
	a2 = sigmoid(z2); % (25*1)
    
    a2 = [1 ; a2]; % adding a bias (26*1)
	z3 = Theta2 * a2; % (10*26)*(26*1)
	a3 = sigmoid(z3); % final activation layer a3 == h(theta) (10*1)
    
    % Step 2
	delta_3 = a3 - y_new(:,t); % (10*1)
	
    z2=[1; z2]; % bias (26*1)
    % Step 3
    delta_2 = (Theta2' * delta_3) .* sigmoidGradient(z2); % ((26*10)*(10*1))=(26*1)

    % Step 4
	delta_2 = delta_2(2:end); % skipping sigma2(0) (25*1)

	Theta2_grad = Theta2_grad + delta_3 * a2'; % (10*1)*(1*26)
	Theta1_grad = Theta1_grad + delta_2 * a1'; % (25*1)*(1*401)
    
end;

% Step 5
Theta2_grad = (1/m) * Theta2_grad; % (10*26)
Theta1_grad = (1/m) * Theta1_grad; % (25*401)


for i = 1:m; 
   a1 = X(i, :)
   delta_3 = a3(i,:) - y(i,:);
   delta_2 = (Theta2'*delta_3') .* a2(i,:) .* (1-a2(i,:));
   Theta1_grad += ((delta_2')*a2(i)')
   size(delta_2)
   size(h2(i,:))
   Theta2_grad += delta_2*a3(i)
endfor
%J += sum(Theta1) + sum(Theta2)
%
  %J += y(:,k)*log(h2(:,k) + (1-y(:,k)*log(1-h2(:,k))
end
